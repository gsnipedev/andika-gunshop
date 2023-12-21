import type {ProductEntity} from "@/model/ProductScheme";
import type {Pageable} from "@/model/domain/Pageable";

export class ProductService {
    static async GetAll(pageable: Pageable): Promise<ProductEntity[]> {

        const url: string = `http://localhost:8080/product?page=${pageable.page}&size=${pageable.size}&sort=${pageable.sort}`

        const response: Response = await fetch(url, {
            method: 'GET',
            headers: {
                Accept: '*/*',
                'Accept-Encoding': 'gzip,deflate,br',
                'Content-Type': 'application/json',
                Connection: 'keep-alive',
                'Authorization' : localStorage.getItem("authToken") ?? ""
            }
        })

        return (await response.json()) as ProductEntity[]
    }

    static async GetOne(id: number): Promise<ProductEntity> {

        const url: string = `http://localhost:8080/product/${id}`

        const response: Response = await fetch(url, {
            method: 'GET',
            headers: {
                Accept: '*/*',
                'Accept-Encoding': 'gzip,deflate,br',
                'Content-Type': 'application/json',
                Connection: 'keep-alive',
                'Authorization' : localStorage.getItem("authToken") ?? ""
            }
        })

        return (await response.json()) as ProductEntity
    }
}