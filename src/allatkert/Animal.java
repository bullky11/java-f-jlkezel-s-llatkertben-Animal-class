
package allatkert;

public class Animal {
   private String faj;
   private int kor;
   private String lelohely;
   private String etrend;
   private int kifutoszam;
   
   public Animal(String sor){
       sor = sor.replace(",", ".");
       String[] s =sor.split(";");
        this.faj = s[0];
        this.kor = Integer.parseInt(s[1]);
        this.lelohely = s[2];
        this.etrend = s[3];
        this.kifutoszam = Integer.parseInt(s[4]);
    }
   

    public Animal(String faj, int kor, String lelohely, String etrend, int kifutoszam) {
        this.faj = faj;
        this.kor = kor;
        this.lelohely = lelohely;
        this.etrend = etrend;
        this.kifutoszam = kifutoszam;
    }

    public String getFaj() {
        return faj;
    }

    public int getKor() {
        return kor;
    }

    public String getLelohely() {
        return lelohely;
    }

    public String getEtrend() {
        return etrend;
    }

    public int getKifutoszam() {
        return kifutoszam;
    }

    @Override
    public String toString() {
        return "Animal{" + "faj=" + faj + ", kor=" + kor + ", lelohely=" + lelohely + ", etrend=" + etrend + ", kifutoszam=" + kifutoszam + '}';
    }
 
}
