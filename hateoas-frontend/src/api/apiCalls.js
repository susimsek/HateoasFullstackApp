import axios from "axios";

export const instance = axios.create({
    baseURL: process.env.REACT_APP_API_URL
});

export const getAllCapabilities = (getLink) => {
    if (!getLink) {
        getLink = '/api/paged-capabilities?page=0&size=3';
    }
    return instance.get(getLink);
}

export const deleteCapability = (deleteLink) => {
    return instance.delete(deleteLink);
}

export const addCapability = (capability, postLink) => {
    return instance.post(postLink, capability);
}

export const updateCapability = (capability, updateLink) => {
    return instance.put(updateLink, capability);
}