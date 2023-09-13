import { Category } from "./Category";

export class AttachementDto {
    id!: string ;
    fileName!: string ;
    downloadURL!: string ;
    fileType!: string ;
    fileSize!: number;
    category!: Category;
    addAt!: Date;
}