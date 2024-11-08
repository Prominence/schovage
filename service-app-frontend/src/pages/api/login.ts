import type {APIRoute} from "astro";
import {logIn} from "../../services/authService.ts";

export const POST: APIRoute = async ({ request }) => {
    const data = await request.formData();
    const email = data.get("email") as string;
    const password = data.get("password") as string;

    // Validate the data - you'll probably want to do more than this
    if (!email || !password) {
        return new Response(
            JSON.stringify({
                message: "Missing required fields",
            }),
            { status: 400 }
        );
    }

    try {
        const authToken = await logIn(request, email, password);
        const headers = new Headers();
        headers.append('Set-Cookie', `schovage-access-token=${authToken.accessToken}; HttpOnly; SameSite=Lax; Path=/; Max-Age=${authToken.accessTokenExpiresIn}`);
        headers.append('Set-Cookie', `schovage-refresh-token=${authToken.refreshToken}; HttpOnly; SameSite=Lax; Path=/; Max-Age=${authToken.refreshTokenExpiresIn}`);

        return new Response(
            null,
            {
                status: 200,
                headers
            }
        )
    } catch (e) {
        return new Response(
            JSON.stringify({
                message: "Wrong username or password"
            }),
            {
                status: 403
            }
        );
    }
}