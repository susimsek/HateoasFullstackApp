import axios from "axios";

export const getAllCapabilities = (getLink) => {
    if (!getLink) {
        getLink = '/paged-capabilities?page=0&size=3';
    }
    return axios.get(getLink);
}

export const deleteCapability = (deleteLink) => {
    return axios.delete(deleteLink);
}

export const addCapability = (capability, postLink) => {
    return axios.post(postLink, capability);
}

export const updateCapability = (capability, updateLink) => {
    return axios.put(updateLink, capability);
}