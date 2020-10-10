# JJDDR1 - Zadanie domowe nr. 2

## Cel
Głównym celem jest utrwalenie i sprawdzenie umiejętności związanych z przetwarzaniem JSONa i implementacji kolekcji danych.
Przygotowałem prostą aplikację, która powinna mieć możliwość przetwarzania zebranych logów i generowania raportów z określonego zakresu danych.

Rozwiązanie należy przesłać do czwartku, 18 października, do godziny 23:59, na mój adres mailowy: [tomasz.odwald@gmail.com](mailto:tomasz.odwald@gmail.com?subject=Praca%20domowa%202%20-%20imie%20nazwisko) lub na Slacku.

## Zadania

### Klasa LogDataSet - 25 pkt.

#### 1. Implementacja metod z interfejsu Collection - 6 pkt.
Wymagana jest implementacja wszystkich metod zwracających wyjątek: "Method ... is not implemented".

#### 2. Testy jednostkowe w klasie TestLogDataSet zwracają pass - 9 pkt.
Zaimplementowałem za was logikę odnośnie wzorca singleton bezpiecznego dla wątków, więc 2 z 11 testów będą na pass.
Waszym zadaniem jest sprawić, aby reszta testów również była na zielono :)

#### 3. Utwórz gettery dla kolekcji - 4 pkt.
Będą potrzebne 2 gettery: getByComponentName i getByDate.

#### 4. Napisz testy jednostkowe dla utworzonych getterów - 6 pkt.
Testy powinny pokrywać wszystkie możliwe sytuacje odnośnie przekazanych parametrów.

### Klasa LogsProvider - 15 pkt.

#### 1. Klasa dziedziczącą po tej klasie abstrakcyjnej - 2 pkt.
Klasa ma służyć dostarczaniu logów z aplikacji webowej, więc proszę o uwzględnienie tego w nazewnictwie.

#### 2. Klasy modelów danych odpowiadających strukturze pliku z logami - 6 pkt.
Pamiętaj o konwencji nazewnictwa zmiennych w Javie i jak odnosi się to do kluczy JSONa. 

#### 3. Implementacja metody loadLogs - 4 pkt.
Metoda dostaje ścieżkę do pliku JSON, a zwraca listę logów. Należy uwzględnić wszystkie możliwe wyjątki, które wystąpią podczas wczytywania danych.

#### 4. Metoda parsująca dane z pliku JSON na obiekty typu LogData - 3 pkt.
Format danych w pliku JSON nie powinna nas ograniczać w kwestii hierarchi klas w Javie i chcielibyśmy je przekonwertować na obiekty innego typu.

### Klasa Report - 6 pkt.

#### 1. Metody factory do tworzenia raportów dla komponentu i dnia - 4 pkt.
Klasa Report posiada prywatny konstruktor i tego chcielibyśmy się trzymać. Zamiast tworzyć różne konstruktory, lepszym podejściem jest zastosowanie metod factory.
Są to statyczne metody, które zwracają obiekt danego typu i szczegółowo opisują czym będzie ten obiekt.

Waszym zadaniem jest stworzyć dwie metody factory getReportForComponent i getReportForDate, którym przekazujemy odpowiednio nazwę komponentu i datę.

#### 2. Wypisanie raportu dla komponentu "database" i dnia "18.09" - 2 pkt.

W metodzie main utwórz dwa obiekty raportów i wypisz listę logów dla każdego z nich.

### Ogólna ocena kodu - 4 pkt.

Zwróćcie szczególną uwagę na stylistykę kodu. Pierwsze co zrobię, to uruchomię Analyze->Inspect code i będę przeglądać wszystkie warningi ;)

## Zadanie dodatkowe

Jako zadanie dodatkowe chciałbym, żebyście spróbowali utworzyć servlet, który będzie potrafił w wyświetlić jakiś raport, korzystając oczywiście z szablonu FreeMarkera :)