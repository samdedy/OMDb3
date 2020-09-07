
package id.sam.omdb3.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SearchList implements Serializable, Parcelable
{

    @SerializedName("Search")
    @Expose
    private List<Search> search = null;
    @SerializedName("totalResults")
    @Expose
    private String totalResults;
    @SerializedName("Response")
    @Expose
    private String response;
    public final static Creator<SearchList> CREATOR = new Creator<SearchList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SearchList createFromParcel(Parcel in) {
            return new SearchList(in);
        }

        public SearchList[] newArray(int size) {
            return (new SearchList[size]);
        }

    }
    ;
    private final static long serialVersionUID = 6094008651142644508L;

    protected SearchList(Parcel in) {
        in.readList(this.search, (Search.class.getClassLoader()));
        this.totalResults = ((String) in.readValue((String.class.getClassLoader())));
        this.response = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public SearchList() {
    }

    /**
     * 
     * @param search
     * @param totalResults
     * @param response
     */
    public SearchList(List<Search> search, String totalResults, String response) {
        super();
        this.search = search;
        this.totalResults = totalResults;
        this.response = response;
    }

    public List<Search> getSearch() {
        return search;
    }

    public void setSearch(List<Search> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(search);
        dest.writeValue(totalResults);
        dest.writeValue(response);
    }

    public int describeContents() {
        return  0;
    }

}
