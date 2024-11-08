import {getClient} from "../api/client.ts";
import type {GeneralStatistics} from "../interface/models.ts";

export const getGeneralStatistics = async (request: Request) => {
    try {
        const response = await getClient(request).get(`/api/v1/statistics/general`);

        return response.data as GeneralStatistics;
    } catch (ex) {
        console.error(ex);
        throw new Error("Cannot fetch general statistics");
    }
}