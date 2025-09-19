# Importeringsveiledning for eksisterende arbeid

## 🔄 Slik importerer du eksisterende kursarbeid

### 1. Identifiser og organiser filer
Før du starter, lag en oversikt over hva du har:
```bash
# Eksempel på typiske filer du kan ha:
~/Documents/NTNU/
├── TDT4100_ovinger/
├── IT1901_prosjekt/
├── matematikk_notater.pdf
├── algoritmer_oppgaver/
└── diverse_kode/
```

### 2. Opprett kursstruktur
```bash
# Naviger til NTNU-repositoriet
cd /path/to/NTNU

# Opprett struktur for relevant semester
mkdir -p 2024/Høst/TDT4100/{øvinger,forelesninger,prosjekt,notater,eksamen}
mkdir -p 2024/Høst/IT1901/{øvinger,forelesninger,prosjekt,notater,eksamen}
```

### 3. Flytt og organiser filer
```bash
# Kopier øvinger
cp -r ~/Documents/NTNU/TDT4100_ovinger/* 2024/Høst/TDT4100/øvinger/

# Kopier prosjektfiler
cp -r ~/Documents/NTNU/IT1901_prosjekt/* 2024/Høst/IT1901/prosjekt/

# Flytt notater
cp ~/Documents/NTNU/matematikk_notater.pdf 2024/Høst/TMA4100/notater/
```

### 4. Konverter og tilpass filformat

#### For kode-filer:
- Sjekk at filnavn er beskrivende
- Legg til kommentarer hvis nødvendig
- Organiser i passende undermapper

#### For dokumenter:
- Konverter Word-dokumenter til Markdown hvis mulig
- Behold PDF-er for komplekse dokumenter
- Opprett README-filer for å forklare innhold

### 5. Oppdater kurs-README
Kopier `COURSE_TEMPLATE.md` og tilpass:
```bash
cp COURSE_TEMPLATE.md 2024/Høst/TDT4100/README.md
# Rediger filen med riktig kursinformasjon
```

### 6. Rydd opp og commit
```bash
# Sjekk status
git status

# Legg til alle nye filer
git add .

# Commit med beskrivende melding
git commit -m "Import TDT4100 assignments and IT1901 project from Høst 2024"

# Push til GitHub
git push
```

## 📋 Sjekkliste for import

- [ ] Alle filer er lagt i riktige mapper
- [ ] Kurs-README er opprettet og utfylt
- [ ] Filnavn er beskrivende og konsistente
- [ ] Sensitive data (passord, private nøkler) er fjernet
- [ ] Store binære filer (>100MB) er vurdert om skal inkluderes
- [ ] README i hovemappen oppdatert hvis nødvendig

## ⚠️ Viktige tips

### Ikke commit følgende:
- Kompilerte filer (`.class`, `.exe`, `.o`)
- Genererte dokumenter (bruk markdown i stedet)
- Store datafiler (bruk Git LFS eller ekstern lagring)
- Personlig informasjon (studentnummer, e-post i kode)

### Anbefalt filstruktur:
```
TDT4100/
├── README.md                 # Kursoversikt
├── øvinger/
│   ├── oving1/
│   │   ├── README.md        # Oppgavebeskrivelse
│   │   ├── src/             # Kildekode
│   │   └── rapport.md       # Rapport/refleksjon
│   └── oving2/
├── prosjekt/
│   ├── README.md            # Prosjektbeskrivelse
│   ├── src/                 # Hovedkildekode
│   ├── docs/                # Dokumentasjon
│   └── tests/               # Tester
└── notater/
    ├── forelesning1.md
    └── sammendrag.md
```

## 🚀 Automatisering med script

Du kan lage et script for å automatisere import:
```bash
#!/bin/bash
# import_course.sh

COURSE_CODE=$1
SEMESTER=$2
YEAR=$3
SOURCE_DIR=$4

# Opprett struktur
mkdir -p "$YEAR/$SEMESTER/$COURSE_CODE"/{øvinger,forelesninger,prosjekt,notater,eksamen}

# Kopier filer
if [ -d "$SOURCE_DIR" ]; then
    cp -r "$SOURCE_DIR"/* "$YEAR/$SEMESTER/$COURSE_CODE/"
    echo "Filer kopiert til $YEAR/$SEMESTER/$COURSE_CODE/"
else
    echo "Kildemappe $SOURCE_DIR finnes ikke"
fi
```

Bruk: `./import_course.sh TDT4100 Høst 2024 ~/Documents/TDT4100_materials/`