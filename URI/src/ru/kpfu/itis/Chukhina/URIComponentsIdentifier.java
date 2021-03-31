package ru.kpfu.itis.Chukhina;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URIComponentsIdentifier {
    private final int URI_GROUPS_NUMBER = 8;
    private String uri;
    private final String uriPattern = "^(?:([^%#:@]*)?://)?(?:([^/:#]*)?:?([^/@?#]*)@)?" +
                                      "(?:([^:/?#]*)?:?(\\d*)?/)?([^?#]*)?\\??([^#]*)#?(.*)$";
    private String[] uriComponents;

    public URIComponentsIdentifier(String uri){
        if(uri == null) throw new NullPointerException("URI is null");
        this.uri = uri;
        this.uriComponents = new String[URI_GROUPS_NUMBER];
        determineComponents();
    }

    @Override
    public String toString() {
        return this.uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        URIComponentsIdentifier that = (URIComponentsIdentifier) o;
        return Arrays.equals(this.uriComponents, that.uriComponents);
    }

    @Override
    public int hashCode() {
        return 59 * Objects.hash(this.uri) + Arrays.hashCode(this.uriComponents);
    }

    public String getScheme(){
        return this.uriComponents[0];
    }

    public String getUser(){
        return this.uriComponents[1];
    }

    public String getPassword(){
        return this.uriComponents[2];
    }

    public String getHost(){
        return this.uriComponents[3];
    }

    public String getPort() {
        return this.uriComponents[4];
    }

    public String getPath(){
        return this.uriComponents[5];
    }

    public String getQuery(){
        return this.uriComponents[6];
    }

    public String getFragment(){
        return this.uriComponents[7];
    }

    private void determineComponents(){
        Pattern uriPattern = Pattern.compile(this.uriPattern, Pattern.CASE_INSENSITIVE);
        Matcher uriMatcher = uriPattern.matcher(this.uri);
        if(uriMatcher.find()){
            for(int i=0; i<URI_GROUPS_NUMBER; i++){
                this.uriComponents[i] = uriMatcher.group(i+1);
                if(this.uriComponents[i] != null && this.uriComponents[i].equals("")) this.uriComponents[i] = null;
            }
        }
    }
}
