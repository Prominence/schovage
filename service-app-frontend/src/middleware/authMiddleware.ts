import type {MiddlewareHandler} from "astro";
import {parse} from "cookie";

export const auth: MiddlewareHandler = async ({ request, redirect }, next)=> {
    const url = new URL(request.url);

    const isApiRequest = url.pathname.startsWith("/api/");
    const isLoginPageRequest = url.pathname === '/login';
    const isAuthenticated = checkAuthentication(request);

    if (isAuthenticated && isLoginPageRequest) {
        return redirect('/');
    }

    if (!isApiRequest && !isLoginPageRequest && !isAuthenticated) {
        return redirect('/login');
    }

    return next();
}

function checkAuthentication(request: Request): boolean {
    const cookieHeader = request.headers.get('Cookie');
    if (!cookieHeader) {
        return false;
    }

    const cookies = parse(cookieHeader);
    const accessTokenCookie = cookies['schovage-access-token'];

    return !!accessTokenCookie;
}