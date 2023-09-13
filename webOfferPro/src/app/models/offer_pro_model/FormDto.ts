import { FieldType } from "./FieldType";

export class FormDto {
    id? : number  | undefined;
    description! : string;
    content! : string;
    type! : FieldType;
    required! : boolean;
}