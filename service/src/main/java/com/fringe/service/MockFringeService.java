package com.fringe.service;

import com.fringe.datacontract.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 9:45 AM
 */
public class MockFringeService implements IFringeService {

    private String[] mockArtistNames = new String[] {"Rolf Hampshire", "Loida Brunelle", "Titus Askins", "Bernardina Donley",
            "Aracelis Rotz", "Hang Cummins", "Jillian Dial", "Benton Dezern", "Hilario Banes", "Clementine Larocca", "Chantal Yarborough",
            "Azhar Ali-Bande"};

    private List<Show> shows;
    private List<Venue> venues;
    private List<Artist> artists;
    private List<ShowTime> futureShowTimes;


    public MockFringeService() {
        initArtists();
        initShows();;
    }

    @Override
    public List<Artist> getAllArtists() {
        return artists;
    }

    @Override
    public List<ShowTime> getAllFutureShowtimes() {
        return futureShowTimes;
    }

    private void initArtists() {
        artists = new ArrayList<Artist>(mockArtistNames.length);
        for (int i=0; i < mockArtistNames.length; i++) {
            Artist artist = new Artist();
            artist.description = "Bicycle rights 8-bit McSweeney's ennui Odd Future. Flexitarian authentic Bushwick, readymade scenester 8-bit fixie kogi. " +
                    "Organic jean shorts aesthetic, meh master cleanse bitters readymade salvia ethical. Wayfarers before they sold out McSweeney's chambray " +
                    "hoodie locavore kitsch freegan. Brooklyn locavore asymmetrical synth typewriter, next level Pitchfork squid Neutra selfies DIY Tumblr " +
                    "sustainable farm-to-table. Slow-carb vegan stumptown locavore pour-over banh mi, biodiesel mustache. Pinterest authentic Pitchfork, " +
                    "mlkshk distillery 3 wolf moon viral dreamcatcher master cleanse actually before they sold out flannel.";
            artist.id = i;
            artist.photos = getMockPhotos();
            artist.productionCompanyName = "LOLWAT PRODUCTIONS";
            artist.photos = getMockPhotos();
            artist.stageName = mockArtistNames[i];
            artists.add(artist);
        }
    }

    private void initShows() {
        boolean odd = false;
        venues = generateVenues();
        futureShowTimes = new ArrayList<ShowTime>();
        shows = new ArrayList<Show>();

        for (int i = 0; i<10; i++) {
            ShowTime showTime = new ShowTime();

            Show show = new Show();
            show.artist = artists.get(i);
            show.currentOccupancy = 10;
            show.maxOccupancy = 20;
            show.id = i;
            show.minimumAge = 12;
            show.performanceType = PerformanceType.CABARET_VAREITY;
            show.originalWork = true;
            show.premier = Premier.WORLD;
            show.numberOfPerformers = 2;
            shows.add(show);

            showTime.show = show;
            showTime.startTime = Calendar.getInstance();
            showTime.startTime.add(Calendar.HOUR, i+1);
            showTime.endTime = Calendar.getInstance();
            showTime.endTime.add(Calendar.HOUR, i+1);
            showTime.venue = new Venue();

            if (odd) {
                showTime.venue = venues.get(0);
            } else {
                showTime.venue = venues.get(1);
            }

            futureShowTimes.add(showTime);
            odd = !odd;
        }
    }

    private List<Photo> getMockPhotos() {
        String[] mockPhotos = new String[]{
            "http://scontent-a.xx.fbcdn.net/hphotos-ash3/s720x720/430_509142519144518_1747500218_n.jpg",
            "http://scontent-b.xx.fbcdn.net/hphotos-frc3/s720x720/226588_509142165811220_1132239389_n.jpg",
            "http://scontent-a.xx.fbcdn.net/hphotos-prn2/s720x720/166709_509142239144546_600566003_n.jpg",
            "http://scontent-a.xx.fbcdn.net/hphotos-ash2/s720x720/599876_509142339144536_147961152_n.jpg",
            "http://scontent-a.xx.fbcdn.net/hphotos-frc3/s720x720/382291_509142582477845_1578650492_n.jpg",
            "http://scontent-b.xx.fbcdn.net/hphotos-ash3/s720x720/555181_509142649144505_371400067_n.jpg",
            "http://scontent-b.xx.fbcdn.net/hphotos-prn1/s720x720/62175_509142685811168_1738624782_n.jpg",
            "http://scontent-b.xx.fbcdn.net/hphotos-prn1/s720x720/563509_509142785811158_15207242_n.jpg"};

        List<Photo> photos = new ArrayList<Photo>(mockPhotos.length);
        for (String url : mockPhotos) {
            Photo photo = new Photo();
            photo.caption = "Skateboard fap McSweeney's, hashtag mumblecore scenester put a bird on it. Cardigan kitsch DIY, lo-fi chillwave keytar synth pop-up next level. Disrupt gentrify kitsch, try-hard church-key art party drinking vinegar salvia ethical XOXO Blue Bottle cred.";
            photo.isLocal = false;
            photo.url = url;
            photos.add(photo);
        }

        return photos;
    }

    private List<Venue> generateVenues() {
        Venue venue = new Venue();
        venue.name = "7 Stages";
        venue.phone = "404-523-7647";
        venue.longDescription = "Where else in Atlanta will you find performances developed around the world as well as in your own backyard?  Since 1979, 7 Stages has been cultivating local, national, and international artists right here in Little Five Points." +
                "Since 1979, 7 Stages has been bringing local, national, and international emerging artwork of social, political, and spiritual importance to Atlanta audiences for 35 years. Artists of all kinds  have found 7 Stages to be a haven in the support and development of new works and methods of collaboration.";
        Photo photo = new Photo();
        photo.url = "http://www.7stages.org/wp-content/uploads/2013/08/7Stages_WEB_ABOUT_image_v1.png";
        photo.isLocal = false;
        photo.caption = "7 stages";

        venue.photos = new ArrayList<Photo>();
        venue.photos.add(photo);
        venue.address = new Address();
        venue.address.addressOne = "1105 Euclid Ave NE";
        venue.address.city = "Atlanta";
        venue.address.state = "GA";
        venue.address.zip = "30307";
        venue.address.latitude = 33.763665;
        venue.address.longitude = -84.350352;


        Venue venue1 = new Venue();
        venue.name = "Horizons School";
        venue.phone = "404-378-2219";
        venue.longDescription = "";

        Photo photo1 = new Photo();
        photo.caption = "Horizons";
        photo.url = "http://horizonsschool.com/wp-content/uploads/2013/10/llama.jpg";
        photo.isLocal = false;
        venue.photos = new ArrayList<Photo>();
        venue.photos.add(photo);

        venue.address = new Address();
        venue.address.addressOne = "900 DeKalb Ave NE";
        venue.address.city = "Atlanta";
        venue.address.state = "GA";
        venue.address.zip = "30307";

        List<Venue> venues = new ArrayList<Venue>();
        venues.add(venue);
        venues.add(venue1);
        return venues;
    }
}
