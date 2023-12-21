export interface LoginResponse {
    authToken: string
}

export interface LoginRequest {
    username: string
    password: string
}

export interface RegisterRequest {
    username: string
    password: string
    passwordRepeat: string
    firstName: string
    lastName: string
}
