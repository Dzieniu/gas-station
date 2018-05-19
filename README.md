# Projekt stacji benzynowej

Celem naszego projektu jest stworzenie aplikacji nadzorującej pracę stacji benzynowej. Stacja pozwala na tankowanie paliw dla samochodów osobowych jak i tirów czy autobusów. Dodatkowo wewnątrz budynku znajduje się sklep oferujący podstawowe towary oraz ciepłe przekąski.

Aby skorzystać ze stacji i zatankować paliwo klient musi podjechać pod odpowiedni dystrybutor. Stacja posiada cztery dystrybutory z paliwami: diesel(ON), PB95, PB98, gaz. Wszystkie paliwa będą miały określoną cenę. Po podjechaniu pod dystrybutor klient wkłada pistolet do wlewu paliwa w samochodzie i po naciśnięciu spustu pistoletu rozpoczyna tankowanie. Na ekranie ma przedstawiony stan paliwa, który do tej pory wlał do baku oraz cenę, którą za to paliwo zapłaci. Po zatankowaniu paliwa klient podchodzi do kasy oraz płaci za paliwo. Opcjonalnie do rachunku można doliczyć zakupy zrobione w sklepie wewnątrz stacji.

Nasza stacja oferuje również program kliencki, klienci mają możliwość założenia karty stałego klienta i zbierania punktów w miare tankowania paliwa, punkty będą również naliczane podczas zakupów w sklepie wewnątrz stacji podczas zakupu towarów lub ciepłych przekąsek. Po uzbieraniu odpowiednie ilości punktów klientom przysługuje stała zniżka na paliwo. Punkty są naliczane według następującego wzoru 1zł wydane = 0.5 punktu. 

* Progi punktowe na konkretne zniżki są następujące:
  * 1000 punktów - zniżka 5%
  * 2000 punktów - zniżka 10%
  * 3500 punktów - zniżka 15%

Zniżki będą obowiązywały tylko na zakup paliwa, nie będą obowiązywały na zakupy w sklepie. Ważność karty klienta wynosi rok. Jeśli przez rok klient nie wykona żadnych transakcji z użyciem swojej karty, zostaje ona unieważniona i klient będzie musiał założyć nową.

Ważną częścią systemu jest obsługa stacji benzynowej, to ona przeprowadza transakcje, kasując produkty, przygotowując gorące przekąski oraz rozliczając poszczególnych klientów z zatankowanego paliwa oraz zakupów w sklepie. Obsługa akceptuje zapłaty zarówno w formie gotówki i jak i z użyciem karty kredytowej. Obsługa wydaje również karty stałego klienta. Klient po podaniu swoich danych zostaje zarejestrowany w systemie oraz dostaje kartę która uprawania go do otrzymywania zniżek klienckich. 

Sprzedawca ma dostęp do widoku z kamer zamontowanych na stacji oraz do podglądu stanu wszystkich zbiorników z paliwami. Stacja pracuje 24 godziny na dobę 7 dni w tygodniu, także w święta.

* Diagramy wykorzystywane przy tworzeniu projektu: 
  * [Diagram przypadków uzycia](https://drive.google.com/uc?id=1ZDv9qqYQX2fj_MTJrrqHKnXw6urB6fdP)
  * [Diagram sekwencji](https://drive.google.com/uc?id=1-_qzN5ntC8HEeZgwd0LOwBeJYqvh-6Lq)
  * [Diagram aktywności](https://drive.google.com/uc?id=1jO8-fsfvouMcUWouTUPlzU4C_NJMZ5He)
  * [Diagram związków encji](https://drive.google.com/uc?id=1m850XDNU7siljMD-GGB10ic1VQY5y-oe)

Raporty z przebiegu prac można znaleźć [tutaj](https://github.com/Dzieniu2/gas-station/blob/master/reports.md)
