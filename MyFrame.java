package com.company;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static javax.swing.SwingConstants.BOTTOM;


public class myFrame extends JFrame implements ActionListener {

    JsonObject jsonobj; //Oggetto JSON

    //*********PROJECT***********//

    String[] currencyCodes = {
            "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN",
            "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL",
            "BSD", "BTC", "BTN", "BWP", "BYN", "BZD", "CAD", "CDF", "CHF", "CLP",
            "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP",
            "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GGP",
            "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG",
            "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD",
            "JOD", "JPY", "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD",
            "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LYD", "MAD", "MDL", "MGA",
            "MKD", "MMK", "MNT", "MOP", "MRO", "MRU", "MUR", "MVR", "MWK", "MXN",
            "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB",
            "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB",
            "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL", "SOS",
            "SRD", "SSP", "STD", "STN", "SVC", "SYP", "SZL", "THB", "TJS", "TMT",
            "TND", "TOP", "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU",
            "UZS", "VEF", "VES", "VND", "VUV", "WST", "XAF", "XAG", "XAU", "XCD",
            "XDR", "XOF", "XPD", "XPF", "XPT", "YER", "ZAR", "ZMW", "ZWL"
    };

    String baseUrl="https://v6.exchangerate-api.com/v6/YOUR_KEY/latest/";

    JTextField importoValuta = new JTextField();
    JButton confirmButton = new JButton();
    JComboBox valute1 = new JComboBox(currencyCodes);
    JComboBox valute2 = new JComboBox(currencyCodes);
    JTextField risultato = new JTextField();
    Font myfont = new Font("LEMON MILK",Font.BOLD,20);

    myFrame() {


        this.setTitle("Convertitore Valute");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450,600);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setResizable(true);
        this.setLayout(new FlowLayout());

        importoValuta.setPreferredSize(new Dimension(400,40));
        importoValuta.setBounds(25,5,400,40);
        importoValuta.setBackground(Color.BLUE);
        importoValuta.setCaretColor(Color.WHITE);
        importoValuta.setForeground(Color.WHITE);
        importoValuta.setFont(myfont);
        importoValuta.setBorder(null);
        importoValuta.setText("Inserisci importo...");

        valute1.setPreferredSize(new Dimension(200,80));
        valute1.setBounds(50,190,200,80);
        valute1.addActionListener(this);

        valute2.setPreferredSize(new Dimension(200,80));
        valute2.setBounds(50,190,200,80);
        valute2.addActionListener(this);

        risultato.setPreferredSize(new Dimension(400,40));
        risultato.setBounds(25,350,400,40);
        risultato.setBackground(Color.BLUE);
        risultato.setCaretColor(Color.WHITE);
        risultato.setForeground(Color.WHITE);
        risultato.setFont(myfont);
        risultato.setBorder(null);
        risultato.setText("Risultato");
        risultato.setEditable(false);

        confirmButton.setText("CONVERTI");
        confirmButton.setVisible(true);
        confirmButton.setFont(myfont);
        confirmButton.setVerticalAlignment(BOTTOM);
        confirmButton.addActionListener(this);



        this.add(importoValuta);
        this.add(valute1);
        this.add(valute2);
        this.add(confirmButton);
        this.add(risultato);
        this.setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==confirmButton){
            try {
                double numeroDaConvertire = Double.parseDouble(importoValuta.getText());
                if(numeroDaConvertire>=0){
                    String stringaValuta1 = String.valueOf(valute1.getSelectedItem());
                    String stringaValuta2 = String.valueOf(valute2.getSelectedItem());
                    double risultatoFinale;

                    makeReq(stringaValuta1);
                    double valoreValuta1 = Double.parseDouble(String.valueOf(jsonobj.getAsJsonObject("conversion_rates").get(stringaValuta1)));
                    double valoreValuta2 = Double.parseDouble(String.valueOf(jsonobj.getAsJsonObject("conversion_rates").get(stringaValuta2)));


                    risultatoFinale = (valoreValuta1*valoreValuta2)*numeroDaConvertire;

                    risultato.setText(risultatoFinale+" "+valute2.getSelectedItem());


                }

            } catch (NumberFormatException e1) {
                // La stringa non è un numero valido
                risultato.setText("Valore non Valido");
            } catch (IOException ex) {
                // Opzione non valida
                ex.printStackTrace();
            }
        }

    }



    public void makeReq(String myUrl) throws IOException {

        //  URL
        String urlStr = baseUrl.concat(myUrl.toUpperCase());

        // Req
        URL url = new URL(urlStr);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert in JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        jsonobj = root.getAsJsonObject();


    }


}
