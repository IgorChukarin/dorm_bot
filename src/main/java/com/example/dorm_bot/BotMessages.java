package com.example.dorm_bot;

public class BotMessages {
    private static final String BUCKET_EMOJI  = "\uD83E\uDEA3";
    private static final String SPONGE_EMOJI = "\uD83E\uDDFD";
    private static final String LIST_EMOJI = "\uD83D\uDCDD";

    public static final String REMOVE_SUCCESS = "✅%s removed from list!";
    public static final String REMOVE_FAILURE = "❌%s is not on the list!";
    public static final String REMOVE_PROMPT = "What should i remove?";

    public static final String CLEANING_ORDER = LIST_EMOJI + "Cleaning order\n%s";

    public static final String NOTHING_TO_BUY = "A-ma-zing! There is nothing to buy!";
    public static final String HAVE_TO_BUY = "This is what you have to buy:\n%s";

}
