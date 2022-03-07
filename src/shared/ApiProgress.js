import React, {useEffect, useState} from 'react';
import {instance} from "../api/apiCalls";

export const useApiProgress = (apiMethod, apiPath, strictPath) => {
     const [pendingApiCall, setPendingApiCall] = useState(false);
     useEffect(() => {
         let requestInterceptor, responseInterceptor;

         const registerInterceptors = () => {
             requestInterceptor = instance.interceptors.request.use((req) => {
                const { url, method } = req;
                 updateApiCallFor(method, url, true);
                 return req;
             });

             responseInterceptor = instance.interceptors.response.use((res) => {
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
             instance.interceptors.request.eject(requestInterceptor);
             instance.interceptors.response.eject(responseInterceptor);
         };

         const updateApiCallFor = (method, url, inProgress) => {

             if (method !== apiMethod) {
                 return;
             }
             if ((strictPath && url === apiPath) || (!strictPath && url.startsWith(apiPath))) {
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