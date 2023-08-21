import { DealStages } from "./dealStages";

export interface Deal {
    dealId: number,
    customerId: number,
    name: string,
    amount: number,
    dealStage: DealStages
}