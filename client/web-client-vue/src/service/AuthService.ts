import type {LoginRequest, LoginResponse, RegisterRequest} from '@/model/AuthScheme'
import type {AccountEntity} from "@/model/AccountScheme";

export class AuthService {
    static async Login(request: LoginRequest): Promise<LoginResponse> {
        const response: Response = await fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: {
                Accept: '*/*',
                'Accept-Encoding': 'gzip,deflate,br',
                'Content-Type': 'application/json',
                Connection: 'keep-alive'
            },
            body: JSON.stringify(request)
        })

        if (response.status === 401) {
            throw new Error('Wrong username or password')
        }

        return (await response.json()) as LoginResponse
    }

    static async Register(request: RegisterRequest): Promise<AccountEntity> {
        const response: Response = await fetch('http://localhost:8080/auth/register', {
            method: 'POST',
            headers: {
                Accept: '*/*',
                'Accept-Encoding': 'gzip,deflate,br',
                'Content-Type': 'application/json',
                Connection: 'keep-alive'
            },
            body: JSON.stringify(request)
        })

        if(response.status != 200){
            throw new Error();
        }

        return (await response.json()) as AccountEntity
    }
}