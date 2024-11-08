import type {APIRoute} from "astro";
import {deleteApiKey} from "../../../services/apiKeysService.ts";

export const DELETE: APIRoute = async ({ request, params }) => {
    const id = params.id as string;

    try {
        await deleteApiKey(request, id);
    } catch (error) {
        return new Response(JSON.stringify({message: (error as any).message}),
            {
                status: 400
            });
    }

    return new Response(null, {
        status: 200,
    });
}