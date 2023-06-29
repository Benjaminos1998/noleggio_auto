import { TipologiaNoleggio } from "../enums/TipologiaNoleggio";
import { Auto } from "./auto";
import { Utente } from "./utente";

export interface Noleggio{
  idNoleggio:number;
  dataInizio:Date;
  dataFine:Date;
  prezzo:number;
  tipoNoleggio:TipologiaNoleggio;
  auto:Auto;
  utente:Utente

}
