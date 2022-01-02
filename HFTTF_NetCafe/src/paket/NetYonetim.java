package paket;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class NetYonetim {
	public static final long HOUR = 3600*1000;
	public static double saatlikUcret = 1.5f;
	public static double kasaParasi=0;
	public static int gunSayisi=1;
	public static void main(String[] args) throws IOException {

		Scanner girdi = new Scanner(System.in);
		int masaSayisi=0;
		System.out.println("Masalar�n Saati ka� tl olsun ?");
		saatlikUcret=girdi.nextInt();
		System.out.println("Kafede ka� masa olsun ? ");
		masaSayisi=girdi.nextInt();
		Kasa kasamiz = new Kasa();


		File file = new File("dosya.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        
        

		String[][] masalar= new String[masaSayisi][5];
		for (int i = 0; i <masaSayisi; i++) {

			Masa masa = new Masa();
			masa.masaNo=Integer.toString(i+1);
			masa.setBaslangicSaati("--:--");
			masa.setBitisSaati("--:--");
			masa.setMasaAcikmi("hay�r");
			masa.setMasaUcret("0 TL");

			String masaNo =masa.masaNo;
			String baslangicSaati=masa.getBaslangicSaati();
			String bitisSaati=masa.getBitisSaati();
			String masaAcikmi=masa.getMasaAcikmi();
			String masaUcret=masa.getMasaUcret();

			System.out.println("Masa "+masa.masaNo + " kuruldu.");
			masalar[i][0]=masaNo;
			masalar[i][1]=baslangicSaati;
			masalar[i][2]=bitisSaati;
			masalar[i][3]=masaAcikmi;
			masalar[i][4]=masaUcret;
		}



		kasaParasi = kasamiz.getAnaPara();


		int secim =0;
		secim=menuGetir();
		do {
			if(secim==1)
			{
				System.out.println("Masa a�ma i�lemleri");
				System.out.println("--------------------");
				masaAc(masalar);
				kasamiz.setAnaPara(kasaParasi);
				secim=menuGetir();
			}
			else if (secim==2)
			{
				System.out.println("Masa Kapatma i�lemleri");
				System.out.println("--------------------");
				masaKapat(masalar);
				secim=menuGetir();
			}else if (secim==3)
			{
				System.out.println("Masa G�r�nt�leme i�lemleri");
				System.out.println("--------------------");
				masalariGoruntule(masalar);
				secim=menuGetir();
			}else if (secim==4)
			{
				System.out.println("Kasa");
				System.out.println("--------------------");
				System.out.println("Has�lat : "+ kasamiz.getAnaPara()+" TL");
				System.out.println("--------------------");
				secim=menuGetir();
				
			}else if (secim==5)
			{
				String para = kasaParasi+" ";
				
				
				System.out.println(gunSayisi+".g�n Bitiyor......");
				FileWriter fileWriter = new FileWriter(file, false);
		        BufferedWriter bWriter = new BufferedWriter(fileWriter);
		        bWriter.write(para);
		        bWriter.close();
		        gunuBitir(masalar);
		        gunSayisi++;
		        System.out.println("* * * * * * * * * * * * * * * * *");
		        System.out.println(" * * * * * * * * * * * * * * * * ");
		        System.out.println("* * * * * * * * * * * * * * * * *");
		        System.out.println(" * * * * * * * * * * * * * * * * ");
		        System.out.println("* * * * * * * * * * * * * * * * *");
		        System.out.println(gunSayisi+".g�n Ba�l�yor......");
		        secim=menuGetir();
			}
			
			
		}while(secim!=6);
	}
	



	private static void gunuBitir(String masalar[][]) {
		
		for(int i =0;i<masalar.length;i++) {
			 

				masalar[i][1]="--:--";
				masalar[i][2]="--:--";
				masalar[i][3]="hay�r";
				masalar[i][4]="0 TL";
				kasaParasi=0;
		}
		
	}




	private static void masaAc(String masalar[][]) {
		Date tarih = new Date();
		DateFormat duzenlesaat = new SimpleDateFormat("HH:mm:ss") {};

		Scanner girdi = new Scanner(System.in);

		System.out.println("Masa Numaras� Giriniz: ");
		int masaNo=girdi.nextInt();
		if(masaNo>masalar.length) {

			System.out.println("�yle bir masam�z yoktur!");

		}
		else {

			System.out.println("Ka� Saat A��laca��n� Giriniz: ");
			int sureSaat=girdi.nextInt();
			Date yeniSaat = new Date(tarih.getTime() + sureSaat * HOUR);


			String suanSaat = duzenlesaat.format(tarih);
			String bitisSaat= duzenlesaat.format(yeniSaat);

			double ucret = sureSaat * saatlikUcret;

			masalar[masaNo-1][1]=suanSaat;
			masalar[masaNo-1][2]=bitisSaat;
			masalar[masaNo-1][3]="evet";
			masalar[masaNo-1][4]=ucret+" TL";

			kasaParasi+=ucret;
			System.out.println(masaNo+" Numaral� masa "+sureSaat+" saat a��lm��t�r.");
		}
	}
	
	
	private static void masaKapat(String[][] masalar) {
		Scanner girdi= new Scanner(System.in);
		System.out.println("Kapat�lacak Masay� se�in: ");
		int secilenMasa=girdi.nextInt();
		if(secilenMasa>masalar.length) {

			System.out.println("�yle bir masam�z yoktur!");

		}else {
		if (masalar[secilenMasa-1][3]=="evet") {

			masalar[secilenMasa-1][1]="--:--";
			masalar[secilenMasa-1][2]="--:--";
			masalar[secilenMasa-1][3]="hay�r";
			masalar[secilenMasa-1][4]="0 TL";
			System.out.println("Masa "+secilenMasa+" Kapat�lm��t�r.");

		}
		else if (masalar[secilenMasa-1][3]=="hay�r")
		{
			System.out.println("Masa A��k De�il.");
		}

		}
	}

	private static int menuGetir() {
		System.out.println("--------------------");
		System.out.println("HFTTF Internet-Cafe Y�netici Ekran�na Ho�geldiniz!..\n\nMasalar�m�z�n Saati "+saatlikUcret+" TLdir.\n");
		System.out.println("Se�iminizi Yap�n�z.");
		System.out.println("1- Masa A�\n2- Masa Kapat\n3- Masa G�r�nt�leme\n4- Kasa\n5- G�n Bitir\n6- ��k��  ");
		System.out.println("--------------------");
		Scanner girdi = new Scanner(System.in);
		int secim=0;
		secim=girdi.nextInt();
		return secim;
	}


	private static void masalariGoruntule(String masalar[][]) {
		for (int i = 0; i < masalar.length; i++) {
			System.out.println("Masa No: "+masalar[i][0]);
			System.out.println("Masa Ba�lang�� Saati: "+masalar[i][1]);
			System.out.println("Masa Biti� Saati: "+masalar[i][2]);
			System.out.println("Masa A��k m�: "+masalar[i][3]);
			System.out.println("Masa Ucreti: "+masalar[i][4]);
			System.out.println("---------------------");
		}

	}

}

