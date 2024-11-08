import type {ApiKey, ApiKeyCreatable, ApiKeyEditable} from "../interface/models.ts";
import {getClient} from "../api/client.ts";

export const getApiKeys = async (request: Request) => {
    try {
        const response = await getClient(request).get(`/api/v1/api-key`);

        return response.data as ApiKey[];
    } catch (ex) {
        console.error(ex);
        throw new Error("Cannot fetch api keys");
    }
}

export const getApiKey = async (request: Request, id: string) => {
    try {
        const response = await getClient(request).get(`/api/v1/api-key/${id}`);

        return response.data as ApiKey;
    } catch (ex) {
        console.error(ex);
        throw new Error(`Cannot fetch api key by id ${id}`);
    }
}

export const createApiKey = async (request: Request, apiKey: ApiKeyCreatable) => {
    try {
        const response = await getClient(request).post(`/api/v1/api-key`, apiKey);

        return response.data as ApiKey;
    } catch (ex) {
        console.error(ex);
        throw new Error(`Cannot create api key`);
    }
}

export const updateApiKey = async (request: Request, id: string, apiKey: ApiKeyEditable) => {
    try {
        const response = await getClient(request).patch(`/api/v1/api-key/${id}`, apiKey);

        return response.data as ApiKey;
    } catch (ex) {
        console.error(ex);
        throw new Error(`Cannot update api key`);
    }
}

export const deleteApiKey = async (request: Request, id: string) => {
    try {
        const response = await getClient(request).delete(`/api/v1/api-key/${id}`);

        return response.status === 200;
    } catch (ex) {
        console.error(ex);
        throw new Error(`Cannot delete api key.`);
    }
}