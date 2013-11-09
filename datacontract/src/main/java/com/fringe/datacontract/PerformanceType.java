package com.fringe.datacontract;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 11:59 AM
 */
public enum PerformanceType {
    CABARET_VAREITY("Cabaret Variety"),
    CIRCUS_CLOWNING("Circus Clowning"),
    DANCE("Dance"),
    MIME_MASK_THEATRE("Mime/Mask Theatre"),
    PUPPETRY("Puppetry"),
    SPOKEN_WORD_STORYTELLING("Spoken Word/Storytelling"),
    THEATRE("Theatre/List Genre"),
    OTHER("Other");

    private final String displayName;

    private PerformanceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}
