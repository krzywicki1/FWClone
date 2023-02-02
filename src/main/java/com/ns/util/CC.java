package com.ns.util;

@SuppressWarnings("unused")
public final class CC { // Console Colors

    public static final String
              RESET                     = "\033[0m"  // Text Reset
    // Regular
            , BLACK                     = "\033[0;30m" // BLACK
            , RED                       = "\033[0;31m" // RED
            , GREEN                     = "\033[0;32m" // GREEN
            , YELLOW                    = "\033[0;33m" // YELLOW
            , BLUE                      = "\033[0;34m" // BLUE
            , PURPLE                    = "\033[0;35m" // PURPLE
            , CYAN                      = "\033[0;36m" // CYAN
            , WHITE                     = "\033[0;37m" // WHITE
    // Bold
            , BLACK_BOLD                = "\033[1;30m" // BLACK
            , RED_BOLD                  = "\033[1;31m" // RED
            , GREEN_BOLD                = "\033[1;32m" // GREEN
            , YELLOW_BOLD               = "\033[1;33m" // YELLOW
            , BLUE_BOLD                 = "\033[1;34m" // BLUE
            , PURPLE_BOLD               = "\033[1;35m" // PURPLE
            , CYAN_BOLD                 = "\033[1;36m" // CYAN
            , WHITE_BOLD                = "\033[1;37m" // WHITE
    // Underline
            , BLACK_UNDERLINED          = "\033[4;30m" // BLACK
            , RED_UNDERLINED            = "\033[4;31m" // RED
            , GREEN_UNDERLINED          = "\033[4;32m" // GREEN
            , YELLOW_UNDERLINED         = "\033[4;33m" // YELLOW
            , BLUE_UNDERLINED           = "\033[4;34m" // BLUE
            , PURPLE_UNDERLINED         = "\033[4;35m" // PURPLE
            , CYAN_UNDERLINED           = "\033[4;36m" // CYAN
            , WHITE_UNDERLINED          = "\033[4;37m" // WHITE
    // Background
            , BLACK_BACKGROUND          = "\033[40m" // BLACK
            , RED_BACKGROUND            = "\033[41m" // RED
            , GREEN_BACKGROUND          = "\033[42m" // GREEN
            , YELLOW_BACKGROUND         = "\033[43m" // YELLOW
            , BLUE_BACKGROUND           = "\033[44m" // BLUE
            , PURPLE_BACKGROUND         = "\033[45m" // PURPLE
            , CYAN_BACKGROUND           = "\033[46m" // CYAN
            , WHITE_BACKGROUND          = "\033[47m" // WHITE
    // High Intensity
            , BLACK_BRIGHT              = "\033[0;90m" // BLACK
            , RED_BRIGHT                = "\033[0;91m" // RED
            , GREEN_BRIGHT              = "\033[0;92m" // GREEN
            , YELLOW_BRIGHT             = "\033[0;93m" // YELLOW
            , BLUE_BRIGHT               = "\033[0;94m" // BLUE
            , PURPLE_BRIGHT             = "\033[0;95m" // PURPLE
            , CYAN_BRIGHT               = "\033[0;96m" // CYAN
            , WHITE_BRIGHT              = "\033[0;97m" // WHITE
    // Bold High Intensity
            , BLACK_BOLD_BRIGHT         = "\033[1;90m" // BLACK
            , RED_BOLD_BRIGHT           = "\033[1;91m" // RED
            , GREEN_BOLD_BRIGHT         = "\033[1;92m" // GREEN
            , YELLOW_BOLD_BRIGHT        = "\033[1;93m" // YELLOW
            , BLUE_BOLD_BRIGHT          = "\033[1;94m" // BLUE
            , PURPLE_BOLD_BRIGHT        = "\033[1;95m" // PURPLE
            , CYAN_BOLD_BRIGHT          = "\033[1;96m" // CYAN
            , WHITE_BOLD_BRIGHT         = "\033[1;97m" // WHITE
    // High Intensity Backgrounds
            , BLACK_BACKGROUND_BRIGHT   = "\033[0;100m" // BLACK
            , RED_BACKGROUND_BRIGHT     = "\033[0;101m" // RED
            , GREEN_BACKGROUND_BRIGHT   = "\033[0;102m" // GREEN
            , YELLOW_BACKGROUND_BRIGHT  = "\033[0;103m" // YELLOW
            , BLUE_BACKGROUND_BRIGHT    = "\033[0;104m" // BLUE
            , PURPLE_BACKGROUND_BRIGHT  = "\033[0;105m" // PURPLE
            , CYAN_BACKGROUND_BRIGHT    = "\033[0;106m" // CYAN
            , WHITE_BACKGROUND_BRIGHT   = "\033[0;107m" // WHITE
            ;
    public static String r(Object o) { return cat(RED, o); }
    public static String g(Object o) { return cat(GREEN, o); }
    public static String b(Object o) { return cat(BLUE, o); }
    public static String y(Object o) { return cat(YELLOW, o); }
    public static String p(Object o) { return cat(PURPLE, o); }
    public static String c(Object o) { return cat(CYAN, o); }
    public static String w(Object o) { return cat(WHITE, o); }

    public static String R(Object o) { return cat(RED_BACKGROUND + WHITE_BRIGHT, o); }
    public static String G(Object o) { return cat(GREEN_BACKGROUND + WHITE_BRIGHT, o); }
    public static String B(Object o) { return cat(BLUE_BACKGROUND + WHITE_BRIGHT, o); }
    public static String Y(Object o) { return cat(YELLOW_BACKGROUND + BLACK, o); }
    public static String P(Object o) { return cat(PURPLE_BACKGROUND + WHITE_BRIGHT, o); }
    public static String C(Object o) { return cat(CYAN_BACKGROUND + WHITE_BRIGHT, o); }
    public static String W(Object o) { return cat(WHITE_BACKGROUND + BLACK, o); }

    private static String cat(String prefix, Object o) { return o == null ? "" : prefix + o + RESET; }
}