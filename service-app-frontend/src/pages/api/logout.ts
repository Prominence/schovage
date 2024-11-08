import type {APIRoute} from "astro";

export const POST: APIRoute = async () => {
    const headers = new Headers();
    headers.append('Set-Cookie', `schovage-access-token=; Max-Age=0; Path=/; HttpOnly`);
    headers.append('Set-Cookie', `schovage-refresh-token=; Max-Age=0; Path=/; HttpOnly`);
    headers.append('Location', '/login');
    return new Response(null, {
        status: 302,
        headers
    });
}