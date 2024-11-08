import {getClient} from "../api/client.ts";
import type {Bucket, BucketEditable} from "../interface/models.ts";

export const getBuckets = async (request: Request) => {
    try {
        const response = await getClient(request).get(`/api/v1/bucket`);

        return response.data as Bucket[];
    } catch (ex) {
        console.error(ex);
        throw new Error("Cannot fetch buckets");
    }
}

export const createBucket = async (request: Request, bucket: BucketEditable) => {
    try {
        const response = await getClient(request).post(`/api/v1/bucket`, bucket);

        return response.data as Bucket;
    } catch (ex) {
        console.error(ex);
        throw new Error(`Cannot create bucket`);
    }
}