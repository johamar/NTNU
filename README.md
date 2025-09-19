# NTNU - Studiearbeid
Dette repositoriet inneholder arbeid fra studier ved NTNU (Norges teknisk-naturvitenskapelige universitet).

## 📁 Struktur

Repositoriet er organisert etter år og semester:
```
NTNU/
├── 2024/
│   ├── Høst/
│   │   ├── TDT4100/    # Objektorientert programmering
│   │   ├── TMA4100/    # Matematikk 1
│   │   └── IT1901/     # Informatikk prosjektarbeid I
│   └── Vår/
│       ├── TDT4120/    # Algoritmer og datastrukturer
│       └── TMA4140/    # Diskret matematikk
├── 2023/
└── ...
```

### Kursmapper
Hver kursmappe inneholder:
- `øvinger/` - Ukentlige øvingsoppgaver
- `forelesninger/` - Notater og kode fra forelesninger  
- `prosjekt/` - Større prosjektarbeid
- `notater/` - Egne notater og sammendrag
- `eksamen/` - Eksamensforberedelser
- `README.md` - Kursinformasjon og nyttige lenker

## 🚀 Hvordan importere arbeid

### 1. Opprett struktur for nytt semester
```bash
mkdir -p YYYY/{Høst,Vår}
```

### 2. Legg til nye kurs
```bash
mkdir -p YYYY/Semester/KURSKODE/{øvinger,forelesninger,prosjekt,notater,eksamen}
```

### 3. Organiser eksisterende filer
- Flytt øvinger til `øvinger/`
- Legg forelesningsnotater i `forelesninger/`
- Samle prosjektfiler i `prosjekt/`

### 4. Opprett kurs-README
Kopier mal fra eksisterende kursmapper og tilpass:
- Kursnavn og -kode
- Studiepoeng
- Relevante lenker

## 📋 Filtyper som ignoreres
Se `.gitignore` for fullstendig liste:
- Kompilerte filer (`.class`, `.o`, `.exe`)
- LaTeX hjelpefiler (`.aux`, `.log`)
- IDE-filer og cache
- Genererte PDF-er (legg til manuelt hvis ønskelig)

## 💡 Tips
- Bruk beskrivende filnavn: `oving1-solution.java` i stedet for `oppgave.java`
- Legg til README i prosjektmapper for å forklare struktur
- Bruk markdown for notater (lettere å lese på GitHub)
- Commit jevnlig med gode commit-meldinger

## 🔗 Nyttige lenker
- [NTNU Studentweb](https://innsida.ntnu.no/studentweb)
- [Blackboard](https://ntnu.blackboard.com/)
- [NTNU IT-hjelpesider](https://innsida.ntnu.no/wiki/-/wiki/Norsk/IT-hjelp)
