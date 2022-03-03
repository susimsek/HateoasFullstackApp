import React, {useEffect, useState} from 'react';
import axios from "axios";

export const useApiProgress = (apiMethod, apiPath, strictPath) => {
     const [pendingApiCall, setPendingApiCall] = useState(false);
     useEffect(() => {
         let requestInterceptor, responseInterceptor;

         const registerInterceptors = () => {
             requestInterceptor = axios.interceptors.request.use((req) => {
                const { url, method } = req;
                 console.log(url);
                 updateApiCallFor(method, url, true);
                 return req;
             });

             responseInterceptor = axios.interceptors.response.use((res) => {
                 const { url, method } = res.config;
                 updateApiCallFor(method, url, false);
                 return res;
             }, (err) => {
                 const { url, method } = err.config;
                 updateApiCallFor(method, url, false);
                 throw err;
             });
         };

         const unregisterInterceptors = () => {
             axios.interceptors.request.eject(requestInterceptor);
             axios.interceptors.response.eject(responseInterceptor);
         };

         const updateApiCallFor = (method, url, inProgress) => {
             if (method !== apiMethod) {
                 return;
             }
             if (strictPath && url === apiPath) {
                 setPendingApiCall(inProgress);
             } else if (!strictPath && url.startsWith(apiPath)) {
                 setPendingApiCall(inProgress);
             }
         }

         registerInterceptors();

         return () => {
             unregisterInterceptors();
         }
     }, [apiPath, apiMethod, strictPath]);
     return pendingApiCall;
}