import {sequence} from "astro/middleware";
import {auth} from "./authMiddleware.ts";

export const onRequest = sequence(auth);