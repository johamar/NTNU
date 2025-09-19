# Forelesning 1 - Objekter og klasser

## Hovedpunkter
- Hva er et objekt?
- Klasser som maler for objekter
- Konstruktører
- Metoder og felt

## Eksempelkode
```java
public class Student {
    private String navn;
    private int studentnummer;
    
    public Student(String navn, int studentnummer) {
        this.navn = navn;
        this.studentnummer = studentnummer;
    }
    
    public String getNavn() {
        return navn;
    }
}
```

## Neste gang
- Arv og polymorfisme