package ru.kpfu.itis.Chukhina;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullURIBuilder {
    private final int URI_GROUPS_NUMBER = 8;
    private String uri;
    private final String uriPattern = "^(?:([^%#:@]*)?://)?(?:([^/:#]*)?:?([^/@?#]*)@)?" +
                                      "(?:([^:/?#]*)?:?(\\d*)?/)?([^?#]*)?\\??([^#]*)#?(.*)$";
    private String[] uriComponents;

    public FullURIBuilder(String uri){
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
        FullURIBuilder that = (FullURIBuilder) o;
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

    private void setScheme(String scheme){
        this.uriComponents[0] = scheme;
        buildURI();
    }

    private void setHost(String host){
        this.uriComponents[3] = host;
        buildURI();
    }

    private void setPort(String port){
        this.uriComponents[4] = port;
        buildURI();
    }

    protected String addScheme(String scheme){
        setScheme(scheme);
        return this.uri;
    }

    protected String addHost(String host){
        setHost(host);
        return this.uri;
    }

    protected String addPort(String port){
        setPort(port);
        return this.uri;
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

    private void buildURI(){
        StringBuilder sb = new StringBuilder();
        if(getScheme() != null){
            sb.append(getScheme()).append("://");
        }
        if(getUser() != null){
            sb.append(getUser()).append("@");
            if(getPassword() == null){
                sb.append("@");
            }
        }
        if(getPassword() != null){
            sb.append(":").append(getPassword()).append("@");
        }
        if(getHost() != null){
            sb.append(getHost());
            if(getPort() == null){
                sb.append("/");
            }
        }
        if(getPort() != null){
            sb.append(":").append(getPort());
        }
        if(getPath() != null){
            sb.append("/").append(getPath());
        }
        if(getQuery() != null){
            sb.append("?").append(getQuery());
        }
        if(getFragment() != null){
            sb.append("#").append(getFragment());
        }
        this.uri = sb.toString();
    }
}
