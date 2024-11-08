import axios from 'axios';
import {parse} from "cookie";

const backendUrl = import.meta.env.BACKEND_URL;

export const getClient = (request: Request) => {
    const apiClient = axios.create({
        baseURL: backendUrl
    });

    apiClient.interceptors.request.use(
        (config) => {
            const cookies = request.headers?.get('Cookie');

            if (cookies) {
                const parsedCookies = parse(cookies);
                const accessToken = parsedCookies['schovage-access-token'];

                if (accessToken) {
                    config.headers.Authorization = `Bearer ${accessToken}`;
                }
            }

            return config;
        },
        (error) => {
            return Promise.reject();
        }
    );

    apiClient.interceptors.response.use(
        (response) => {
            return response;
        },
        async (error) => {
            const originalRequest = error.config;

            // Если токен истек (например, 401 Unauthorized)
            /*if (error.response.status === 401 && !originalRequest._retry) {
                originalRequest._retry = true;  // Флаг для предотвращения повторных попыток
                try {
                    // Запрос на обновление токена (refresh token)
                    const newToken = await refreshToken();  // Ваша функция для получения нового токена

                    // Обновляем заголовок Authorization
                    axios.defaults.headers.common['Authorization'] = `Bearer ${newToken}`;
                    originalRequest.headers['Authorization'] = `Bearer ${newToken}`;

                    // Повторяем запрос с новым токеном
                    return apiClient(originalRequest);
                } catch (refreshError) {
                    // Если обновление токена не удалось, можно перенаправить на страницу логина
                    console.error('Token refresh failed:', refreshError);
                    return Promise.reject(refreshError);
                }
            }*/

            return Promise.reject(error);
        }
    );

    return apiClient;
}
