package org.lostclient.utilities;

import org.lostclient.api.wrappers.map.Area;
import org.lostclient.api.wrappers.map.Tile;

public class Locations {
	public static String currentBranch = "";
	public static String currentLeaf = "";
	public static final Area TUTORIAL_UNDERGROUND = new Area(
			new Tile(3092, 9530, 0),
	        new Tile(3075, 9528, 0),
	        new Tile(3064, 9491, 0),
	        new Tile(3124, 9492, 0),
	        new Tile(3119, 9538, 0));
	public static final Tile RAT_TILE1 = new Tile(3107, 95230);
	public static final Tile RAT_TILE2 = new Tile(3110, 9523,0);
	public static final Tile RAT_TILE3 = new Tile(3110, 9515,0);
	public static final Tile RAT_TILE4 = new Tile(3107, 9512,0);
	public static final Tile RAT_TILE5 = new Tile(3106, 9510,0);
	public static final Area RAT_CAGE = new Area(
			new Tile(3110, 9518, 0),
			new Tile(3109, 9514, 0),
			new Tile(3108, 9514, 0),
			new Tile(3106, 9511, 0),
			new Tile(3104, 9510, 0),
			new Tile(3101, 9510, 0),
			new Tile(3080, 9517, 0),
			new Tile(3096, 9533, 0),
			new Tile(3107, 9523, 0),
			new Tile(3109, 9522, 0));
	public static final Tile CombatTile = new Tile(3106, 9508, 0);
	public static final Tile MinerTile = new Tile(3080, 9504, 0);
	public static final Area GUIDE_AREA = new Area(
			new Tile(3093, 3113, 0),
			new Tile(3097, 3110, 0),
			new Tile(3097, 3105, 0),
			new Tile(3095, 3104, 0),
			new Tile(3096, 3100, 0),
			new Tile(3092, 3100, 0),
			new Tile(3091, 3102, 0),
			new Tile(3087, 3102, 0),
			new Tile(3087, 3107, 0),
			new Tile(3090, 3108, 0));
	public static final Area QUEST_AREA = new Area(
			new Tile(3082, 3126, 0),
			new Tile(3089, 3126, 0),
			new Tile(3089, 3119, 0),
			new Tile(3080, 3119, 0),
			new Tile(3080, 3123, 0));
	public static final Area FISHING_AREA = new Area(
			new Tile(3105, 3089, 0),
			new Tile(3107, 3095, 0),
			new Tile(3107, 3099, 0),
			new Tile(3106, 3100, 0),
			new Tile(3085, 3099, 0),
			new Tile(3090, 3094, 0),
			new Tile(3090, 3090, 0),
			new Tile(3091, 3089, 0),
			new Tile(3101, 3086, 0),
			new Tile(3104, 3088, 0));
	public static final Area GATE_AREA1 = new Area(
			new Tile(3090, 3091, 0),
			new Tile(3089, 3094, 0),
			new Tile(3082, 3097, 0),
			new Tile(3077, 3097, 0),
			new Tile(3079, 3083, 0),
			new Tile(3063, 3077, 0),
			new Tile(3088, 3066, 0),
			new Tile(3089, 3077, 0),
			new Tile(3091, 3079, 0),
			new Tile(3089, 3088, 0));
	public static final Area nav1 = new Area(
			new Tile(3064, 3080, 0),
			new Tile(3053, 3092, 0),
			new Tile(3054, 3101, 0),
			new Tile(3068, 3097, 0),
			new Tile(3077, 3097, 0),
			new Tile(3079, 3095, 0),
			new Tile(3076, 3081, 0));
	public static final Area nav2 = new Area(
			new Tile(3077, 3096, 0),
			new Tile(3071, 3095, 0),
			new Tile(3055, 3102, 0),
			new Tile(3062, 3113, 0),
			new Tile(3073, 3110, 0),
			new Tile(3084, 3107, 0),
			new Tile(3083, 3098, 0));
	public static final Area nav3 = new Area(
			new Tile(3072, 3109, 0),
			new Tile(3062, 3112, 0),
			new Tile(3066, 3134, 0),
			new Tile(3077, 3139, 0),
			new Tile(3082, 3138, 0),
			new Tile(3095, 3134, 0),
			new Tile(3094, 3114, 0),
			new Tile(3087, 3109, 0));
	public static final Tile WIZARD_TILE = new Tile(3141, 3089, 0);
	public static final Area CHICKEN_COOP = new Area(
			new Tile(3141, 3092, 0),
			new Tile(3141, 3096, 0),
			new Tile(3137, 3096, 0),
			new Tile(3137, 3092, 0));
	public static final Area CHICKENS_CASTABLE = new Area(
			new Tile(3137, 3091, 0),
			new Tile(3140, 3089, 0),
			new Tile(3141, 3089, 0),
			new Tile(3141, 3091, 0));
	public static final Area CHURCH_INSIDE = new Area(
			new Tile(3120, 3103, 0),
			new Tile(3120, 3111, 0),
			new Tile(3128, 3110, 0),
			new Tile(3131, 3113, 0),
			new Tile(3134, 3110, 0),
			new Tile(3133, 3106, 0),
			new Tile(3130, 3103, 0));
	public static final Tile CHURCH_TILE = new Tile(3123, 3106, 0);
	public static final Area ACCOUNTGUIDE = new Area(
			new Tile(3125, 3125, 0),
			new Tile(3129, 3125, 0),
			new Tile(3129, 3123, 0),
			new Tile(3125, 3123, 0));
	public static final Area COOKING_AREA = new Area(
			new Tile(3075, 3081, 0),
			new Tile(3078, 3083, 0),
			new Tile(3078, 3085, 0),
			new Tile(3076, 3086, 0),
			new Tile(3076, 3089, 0),
			new Tile(3077, 3091, 0),
			new Tile(3073, 3091, 0),
			new Tile(3073, 3089, 0),
			new Tile(3074, 3089, 0),
			new Tile(3074, 3083, 0));
	public static final Area ACCEPTABLE_COPPERS = new Area(
			new Tile(3084, 9499, 0),
			new Tile(3082, 9501, 0),
			new Tile(3084, 9503, 0),
			new Tile(3086, 9502, 0));
	public static final Area ACCEPTABLE_TINS = new Area(
			new Tile(3077, 9503, 0),
			new Tile(3076, 9503, 0),
			new Tile(3076, 9504, 0),
			new Tile(3075, 9505, 0),
			new Tile(3075, 9506, 0),
			new Tile(3077, 9506, 0));
	public static final Area ACCEPTABLE_TREES = new Area(
			new Tile(3105, 3092, 0),
			new Tile(3106, 3092, 0),
			new Tile(3107, 3099, 0),
			new Tile(3104, 3101, 0),
			new Tile(3098, 3098, 0),
			new Tile(3098, 3097, 0),
			new Tile(3102, 3097, 0));
	public static final Area TUTORIAL_ISLAND_MAIN = new Area(
			new Tile(3176, 3112, 0),
	        new Tile(3170, 3071, 0),
	        new Tile(3143, 3059, 0),
	        new Tile(3123, 3032, 0),
	        new Tile(3034, 3042, 0),
	        new Tile(3044, 3146, 0),
	        new Tile(3088, 3155, 0),
	        new Tile(3103, 3142, 0),
	        new Tile(3123, 3145, 0),
	        new Tile(3134, 3142, 0),
	        new Tile(3150, 3130, 0),
	        new Tile(3166, 3130, 0));
    public static final Area GEArea = new Area(
			new Tile(3142, 3491, 0),
			new Tile(3139, 3495, 0),
			new Tile(3139, 3512, 0),
			new Tile(3143, 3516, 0),
			new Tile(3157, 3516, 0),
			new Tile(3161, 3513, 0),
			new Tile(3169, 3514, 0),
			new Tile(3171, 3516, 0),
			new Tile(3188, 3516, 0),
			new Tile(3197, 3507, 0),
			new Tile(3197, 3507, 0),
			new Tile(3197, 3486, 0),
			new Tile(3191, 3479, 0),
			new Tile(3186, 3475, 0),
			new Tile(3186, 3468, 0),
			new Tile(3144, 3468, 0),
			new Tile(3139, 3472, 0),
			new Tile(3139, 3481, 0),
			new Tile(3142, 3485, 0));
	public static Tile GE = new Tile(3165,3487,0);
	
}
