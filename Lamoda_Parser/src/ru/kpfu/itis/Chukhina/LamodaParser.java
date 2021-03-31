package ru.kpfu.itis.Chukhina;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LamodaParser {
    private List<String> items;

    public LamodaParser(){
        this.items = fill();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        LamodaParser that = (LamodaParser) o;
        return Objects.equals(this.items, that.items);
    }

    @Override
    public int hashCode() {
        return 59 * Objects.hash(this.items) + 31;
    }

    public void show(){
        for(String st : this.items){
            System.out.println(st);
        }
    }

    private List<String> findAllRef(){
        String refRegex = "<a class.*?href=\"(.*?)\".*?products-list-item__link link</span>";
        List<String> ref = findAll(findAllItems(), refRegex, 1);
        ref = checkReferences(ref);
        return ref;
    }

    private List<String> findAllPrices(){
        String priceRegex = "<span class=\".*?\">data-price</span>=\"<span class=\".*?\">(\\d*)?</span>.*?href";
        List<String> prices = findAll(findAllItems(), priceRegex, 1);
        return prices;
    }

    private List<String> findAllNames(){
        String nameRegex = "class=\"products-list-item__img\".*?alt=\"(.*?)\"";
        List<String> names = findAll(findAllItems(), nameRegex, 1);
        return names;
    }

    private List<String> findAllItems(){
        String itemRegex = "\"<span class=\"html-attribute-value\">products-list-item m_loading</span>\".*?Подробнее";
        List<String> items = findAll(readFile(), itemRegex, 0);
        return items;
    }

    private List<String> checkReferences(List<String> ref){
        String[] arr = new String[ref.size()];
        int k=0;
        for(String el : ref){
            arr[k++] = el;
        }
        ref.clear();
        FullURIBuilder uriCI;
        for(int i=0; i< arr.length; i++){
            uriCI = new FullURIBuilder(arr[i]);
            if(uriCI.getScheme() == null){
                arr[i] = uriCI.addScheme("https");
            }
            if(uriCI.getHost() == null){
                arr[i] = uriCI.addHost("www.lamoda.ru");
            }
            if(uriCI.getPort() == null){
                arr[i] = uriCI.addPort("80");
            }
            ref.add(arr[i]);
        }
        return ref;
    }

    private List<String> findAll(List<String> src, String regex, int... group){
        List<String> list = new ArrayList<>();
        for(String line : src) {
            Matcher matcher = Pattern.compile(regex, Pattern.DOTALL + Pattern.CASE_INSENSITIVE).matcher(line);
            String result = "";
            while (matcher.find()) {
                StringBuilder sb = new StringBuilder();
                for (int num : group) {
                    result = matcher.group(num);
                    if (result != null) sb.append(result);
                }
                list.add(sb.toString());
            }
        }
        return list;
    }

    private List<String> fill(){
        List<String> list = new ArrayList<>();
        List<String> names = findAllNames();
        List<String> references = findAllRef();
        List<String> prices = findAllPrices();
        String desc = "";
        for(int i=0; i < names.size(); i++){
            desc = names.get(i) + "; " + prices.get(i) + "; " + references.get(i) + ".";
            list.add(desc);
        }
        return list;
    }

    private List<String> readFile(){
        List<String> lines = new ArrayList<>();
        String path = "lamoda/src/ru/kpfu/itis/Chukhina/Data/lamoda.html";
        try {
            lines = Files.readAllLines(Paths.get(path));
        }catch(FileNotFoundException ex){
            ex.getMessage();
            System.out.println("File doesn't exist");
        }catch(IOException ex){
            ex.getMessage();
            System.out.println("Problems...");
        }
        return lines;
    }
}
