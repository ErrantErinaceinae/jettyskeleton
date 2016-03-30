package webapp;
import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import com.sun.syndication.feed.module.mediarss.types.MediaContent;
import com.sun.syndication.feed.module.mediarss.types.UrlReference;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;

@Path("/")
@Produces("text/plain")
public class Resource {

    @GET
    public String hello() {
        return "hello";
    }


    @GET
    @Path("/api/feed/{userName}")
    public String getUserFeed(@PathParam("userName") String userName) throws IOException, FeedException, URISyntaxException {
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_1.0");

        feed.setTitle("Instagram feed for "+userName);
        feed.setLink("https://www.instagram.com/"+userName);
        feed.setDescription("A100 Aggregation Challenge");
        SyndFeedOutput output = new SyndFeedOutput();
        StringWriter writer = new StringWriter();
        output.output(feed,writer);
        return writer.toString();
    }

}