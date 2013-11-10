package com.fringe.service;

import com.fringe.datacontract.*;

import java.util.*;

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
        Collections.sort(futureShowTimes, ShowTime.showTimeComparator);

        return futureShowTimes;
    }

    private void initArtists() {
        artists = new ArrayList<Artist>(mockArtistNames.length);
        for (int i=0; i < mockArtistNames.length; i++) {
            Artist artist = new Artist();
            artist.description = "I am an artist. HERE ME ROAR!!!!!";
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
            show.numberOfPerformers = 2;
            show.title = "The best show ever";
            show.description = "this is a show about things that are awesome. prepare for lots of laughs and fun.";
            shows.add(show);


            showTime.show = show;
            showTime.startTime = Calendar.getInstance();
            showTime.startTime.add(Calendar.HOUR, i+1);
            showTime.endTime = Calendar.getInstance();
            showTime.endTime.add(Calendar.HOUR, i+1);

            int venue = new Random().nextInt(3);
            showTime.venue = venues.get(venue);

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
        venue.longDescription = "Where else in Atlanta will you find performances developed around the world as well as in your own backyard?  Since 1979, 7 Stages has been cultivating local, national, and international artist right here in Little Five Points." +
                "Since 1979, 7 Stages has been bringing local, national, and international emerging artwork of social, political, and spiritual importance to Atlanta audiences for 35 years. Artists of all kinds  have found 7 Stages to be a haven in the support and development of new works and methods of collaboration.";
        Photo photo = new Photo();
        photo.url = "http://staceybode.com/blog/wp-content/uploads/2013/06/Atlanta-GA-Wedding-Portrait-Photography-7-Stages-0019.jpg";
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
        venue1.name = "Horizons School";
        venue1.phone = "404-378-2219";
        venue1.longDescription = "Horizons is a small k-12 independent, democratic boarding and day school with approximately 110 students located in downtown Atlanta. Our goals are to expand students’ confidence in their abilities, to help students make the transition from external motivation to self-motivation and to promote academic excellence.";

        Photo photo1 = new Photo();
        photo1.caption = "Horizons";
        photo1.url = "http://clatl.com/imager/big-city-burlesque-at-7-stages/b/original/1415215/7c87/image_gallery1-004.jpg";
        photo1.isLocal = false;
        venue1.photos = new ArrayList<Photo>();
        venue1.photos.add(photo1);

        venue1.address = new Address();
        venue1.address.addressOne = "1900 DeKalb Ave NE";
        venue1.address.city = "Atlanta";
        venue1.address.state = "GA";
        venue1.address.zip = "30307";
        venue1.address.latitude = 33.755004;
        venue1.address.longitude = -84.357966;

        Venue venue2 = new Venue();
        venue2.name = "The Tabernacle";
        venue2.phone = "404-659-9022";
        venue2.longDescription = "Larger than the other venues on this list, the Tabernacle is a former church that has been converted into a club. It can accommodate 2600 people. Check out the old-school fixtures, paintings and chandelier that all lend charm to the place. A diverse group of artists play through, although generally it hosts bands that have broken through into the main stream of pop and rock. If your sick of the smoke frequently found at the other venues on this list, smokers are relegated to the basement at Tabernacle. No spot at Tabernacle is bad to watch a show: you can sit in one of the balcony seats, or stand in the pit on the floor – keep this in mind when getting your tickets";

        Photo photo2 = new Photo();
        photo2.caption = "Tabernacle";
        photo2.url = "http://i114.photobucket.com/albums/n269/burnthday/2012%20Tour/0127-1.jpg?t=1330094855";
        photo2.isLocal = false;
        venue2.photos = new ArrayList<Photo>();
        venue2.photos.add(photo2);

        venue2.address = new Address();
        venue2.address.addressOne = "152 Luckie St NW";
        venue2.address.city = "Atlanta";
        venue2.address.state = "GA";
        venue2.address.zip = "30303";
        venue2.address.latitude = 33.7589371;
        venue2.address.longitude = -84.3914396;



        List<Venue> venues = new ArrayList<Venue>();
        venues.add(venue);
        venues.add(venue1);
        venues.add(venue2);
        return venues;
    }
}
