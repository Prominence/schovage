export interface AuthToken {
    readonly accessToken: string;
    readonly refreshToken: string;
    readonly accessTokenExpiresIn: number;
    readonly refreshTokenExpiresIn: number;
}

export interface ApiKeyEditable {
    name: string;
}

export interface ApiKeyCreatable extends ApiKeyEditable{
    bucket: string;
}

export interface ApiKey extends ApiKeyCreatable {
    readonly id: string;
    readonly key: string;
    readonly owner: string;
    readonly created_at: number;
}

export interface BucketEditable {
    name: string;
}

export interface Bucket extends BucketEditable {
    readonly id: string;
    readonly created_at: number;
    readonly documents_count: number;
}

export interface GeneralStatistics {
    readonly total_buckets: number;
    readonly storage_used: number;
    readonly api_request_count: number;
}