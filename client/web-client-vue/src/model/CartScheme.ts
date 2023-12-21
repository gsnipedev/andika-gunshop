import type {ProductEntity} from "@/model/ProductScheme";

export class CartItem {
    item: ProductEntity
    quantity: number
}