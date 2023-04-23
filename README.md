APPLICAZIONE NOLEGGI AUTO


SOFTWARE LIFE CYCLE

Si è scelto di procedere seguendo uno dei metodi Agile: Extreme Programming. Questo promuove lo sviluppo iterativo ed incrementale organizzato in brevi cicli di sviluppo, detti interazioni.
Lo sviluppo è accompagnato dalla stesura di un piano di lavoro che viene continuamente aggiornato a intervalli brevi e regolari. 
Dal punto di vista dell’implementazione, Extreme Programming promuove la scrittura di soluzioni semplici che possono essere adattate e migliorate in un secondo momento attraverso refactoring o scrittura di componenti aggiuntive. Nell’Extreme Programming lo sviluppo prevede che tutti lavorino su tutto alternandosi durante alcune fasi, favorendo così il rendimento e mantenendo alto il livello di concentrazione.
Cinque principi:

•	Feedback rapido: 
ogni volta che vengono aggiornate parti del sistema queste vengono controllate e testate dagli sviluppatori, in quanto si pensa di consegnare sempre una versione funzionante al cliente.

•	Semplicità: 
si vuole evitare di crearsi ulteriore complessità, per cui si cerca di mantenere un design semplice e di aggiungere funzionalità solo quando necessario ed il codice sarà oggetto di refactoring.

•	Cambiamento incrementale: 
non si prendono decisioni improvvise, i cambiamenti vengono effettuati in maniera graduale

•	Abbracciare il cambiamento: 
non si fanno pianificazioni a lungo termine, i problemi più gravi vengono risolti il prima possibile.

•	Lavoro di qualità: 
si cerca di offrire sempre un prodotto qualitativo

CONFIGURATION MANAGEMENT

Per la Configuration Management abbiamo scelto di utilizzare Github. Questo permette attraverso una serie di funzioni di gestire al meglio il progetto.
Per la realizzazione del progetto abbiamo cercato di sfruttare la possibilità di lavorare su branch locali in modo da caricare solo le versioni definitive e funzionanti sul branch main.
Quando sono state effettuate delle modifiche di cui era necessario il giudizio degli altri membri del team abbiamo utilizzato le pull request (come nei casi di alcuni dubbi sui diagrammi per esempio) oppure sono state create delle issues per simili motivi. 
PEOPLE MANAGEMENT AND TEAM ORGANIZATION
Il team è composto da tre persone:

•	Alexander Rubino (MAT. 1064467)

•	Ibrahima Sarr (MAT. 1046446)

•	Beniamino Infante (MAT.1063452)

La struttura del team è semplice: tutti e tre i membri sono al centro del progetto e la coordinazione avviene mediante diretta supervisione.
Il lavoro viene suddiviso in maniera proporzionale, assegnando uno o due diagrammi UML per ogni singolo membro. Per la stesura del codice invece si è scelto di lavorare in coppia, alternandoci di volta in volta per favorire il rendimento e quindi il risultato finale.
La comunicazione è alla base del progetto, infatti sono fissate riunioni periodiche per riepilogare il lavoro svolto, discutere dei problemi incontrati e fissare nuovi obiettivi da raggiungere nel breve periodo.

SOFTWARE QUALITY

Il sistema deve essere conforme allo standard IEEE 9126. 
Questo prevede i seguenti attributi:

•	Funzionalità: 
il sistema deve essere sicuro e deve contenere le funzioni base descritte nei requisiti non funzionali.

•	Affidabilità: 
il sistema deve avere una buona tolleranza agli errori e quindi non causare problemi gravi all’utente finale.

•	Usabilità: 
il sistema deve essere facilmente utilizzabile dall’utente finale.

•	Efficienza: 
il sistema non deve usare risorse eccessive. Deve essere ottimizzato per l’uso immediato.

•	Manutenzione: 
il sistema deve essere regolarmente aggiornato risolvendo failure o bug incontrati e aggiungendo nuove funzioni richieste dall’utente.

•	Portabilità: 
il sistema deve poter girare su diversi OS.

REQUIREMENTS ENGINIEERING

Si vuole realizzare un sistema che gestisca il noleggio di auto. 
L’utente dovrà come prima cosa registrarsi al servizio per poi effettuare l’accesso  mediante un classico servizio di autenticazione (email e password).
Durante la fase di registrazione verrà offerta la possibilità di scegliere o meno di partecipare al programma fedeltà che includerà diversi vantaggi che verranno spiegati in seguito.

Vengono offerti tre tipi di noleggi: 

1.	car sharing (noleggio giornaliero o weekend)

2.	noleggio nel breve periodo (max 3 mesi)

3.	noleggio nel lungo periodo (max 1 anno, solo gli utenti aderenti al programma fedeltà possono sceglierlo) 

Una volta selezionato il tipo di noleggio, l’utente dovrà scegliere il tipo di auto da noleggiare tra:
1.	utilitaria

2.	business

3.	luxury

Il programma fedeltà permette agli utenti di raccogliere punti in base ai chilometri percorsi e alle condizioni con cui viene riconsegnata l’auto. I punti permettono di ottenere sconti ed agevolazioni per nuovi noleggi.

TABELLA MOSCOW

•	MUST HAVE: un sistema che permetta la creazione di un noleggio e che permetta di scegliere un’auto
•	SHOULD HAVE: un sistema di registrazione ed autenticazione
•	COULD HAVE: una lista che mostra lo storico dei noleggi per singolo utente
•	WON’T HAVE: la consegna del veicolo a domicilio

MODELING

Per la realizzazione dei diagrammi UML abbiamo utilizzato il tool StarUML
Tutti i diagrammi UML sono stati inseriti all'interno del file "UML_Diagrams_v1.mdj" nella cartella "Diagrammi UML"

SOFTWARE ARCHITECTURE

Per l’architettura del nostro software si è scelto di adottare un approccio di tipo Model View Controller (MVC). Questo modello permette di gestire direttamente i dati, la logica e le regole dell’applicazione.
MVC è caratterizzato da tre componenti:

•	MODEL: rappresenta la logica del sistema fornisce i metodi per accedere ai dati utili dell’applicazione

•	VIEW: visualizza i dati contenuti nel Model graficamente  e si occupa dell’interazione con utenti e agenti

•	CONTROLLER: riceve i comandi dell’utente, generalmente dall’interfaccia grafica (View) e li attua modificando lo stato delle altre due componenti

SOFTWARE TESTING

L' Extreme Programming viene riconosciuto come un Test Driven Developement ed appunto per questo il nostro è stato un approccio orientato al testing. Ogni modifica che è stata guidata dai Test con JUnit e solo dopo vengono implementati al sistema.

