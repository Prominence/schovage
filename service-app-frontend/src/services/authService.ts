import type {AuthToken} from "../interface/models.ts";
import {getClient} from "../api/client.ts";

export const logIn = async (request: Request, email: string, password: string) => {
    try {
        const axiosResponse = await getClient(request).post(`/api/v1/auth/signin`, JSON.stringify({
            username: email,
            password: password
        }), {
            headers: {
                "Content-Type": "application/json"
            }
        });
        return axiosResponse.data as AuthToken;
    } catch (error) {
        throw error;
    }
}