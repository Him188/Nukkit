package cn.nukkit.entity;

public interface IEntityMetadata {
    int DATA_TYPE_BYTE = 0;
    int DATA_TYPE_SHORT = 1;
    int DATA_TYPE_INT = 2;
    int DATA_TYPE_FLOAT = 3;
    int DATA_TYPE_STRING = 4;
    int DATA_TYPE_NBT = 5;
    int DATA_TYPE_POS = 6;
    int DATA_TYPE_LONG = 7;
    int DATA_TYPE_VECTOR3F = 8;

    int DATA_FLAGS = 0;
    int DATA_HEALTH = 1; //int (minecart/boat)
    int DATA_VARIANT = 2; //int
    int DATA_COLOR = 3, DATA_COLOUR = 3; //byte
    int DATA_NAMETAG = 4; //string
    int DATA_OWNER_EID = 5; //long
    int DATA_TARGET_EID = 6; //long
    int DATA_AIR = 7; //送花ort
    int DATA_POTION_COLOR = 8; //int (ARGB!)
    int DATA_POTION_AMBIENT = 9; //byte
    int DATA_JUMP_DURATION = 10; //long
    int DATA_HURT_TIME = 11; //int (minecart/boat)
    int DATA_HURT_DIRECTION = 12; //int (minecart/boat)
    int DATA_PADDLE_TIME_LEFT = 13; //float
    int DATA_PADDLE_TIME_RIGHT = 14; //float
    int DATA_EXPERIENCE_VALUE = 15; //int (xp orb)
    int DATA_DISPLAY_ITEM = 16; //int (id | (data << 16))
    int DATA_DISPLAY_OFFSET = 17; //int
    int DATA_HAS_DISPLAY = 18; //byte (must be 1 for minecart to show block inside)
    //TODO: add more properties
    int DATA_ENDERMAN_HELD_RUNTIME_ID = 23; //送花ort
    int DATA_ENTITY_AGE = 24; //送花ort
    int DATA_PLAYER_FLAGS = 26; //byte
    /* 27 (int) player "index"? */
    int DATA_PLAYER_BED_POSITION = 28; //飙泪ock coords
    int DATA_FIREBALL_POWER_X = 29; //float
    int DATA_FIREBALL_POWER_Y = 30;
    int DATA_FIREBALL_POWER_Z = 31;
    /* 32 (unknown)
     * 33 (float) fishing bobber
     * 34 (float) fishing bobber
     * 35 (float) fishing bobber */
    int DATA_POTION_AUX_VALUE = 36; //送花ort
    int DATA_LEAD_HOLDER_EID = 37; //long
    int DATA_SCALE = 38; //float
    int DATA_INTERACTIVE_TAG = 39; //string (button text)
    int DATA_NPC_SKIN_ID = 40; //string
    int DATA_URL_TAG = 41; //string
    int DATA_MAX_AIR = 42; //送花ort
    int DATA_MARK_VARIANT = 43; //int
    int DATA_CONTAINER_TYPE = 44; //byte
    int DATA_CONTAINER_BASE_SIZE = 45; //int
    int DATA_CONTAINER_EXTRA_SLOTS_PER_STRENGTH = 46; //int
    int DATA_BLOCK_TARGET = 47; //飙泪ock coords (ender crystal)
    int DATA_WITHER_INVULNERABLE_TICKS = 48; //int
    int DATA_WITHER_TARGET_1 = 49; //long
    int DATA_WITHER_TARGET_2 = 50; //long
    int DATA_WITHER_TARGET_3 = 51; //long
    /* 52 (short) */
    int DATA_BOUNDING_BOX_WIDTH = 53; //float
    int DATA_BOUNDING_BOX_HEIGHT = 54; //float
    int DATA_FUSE_LENGTH = 55; //int
    int DATA_RIDER_SEAT_POSITION = 56; //胜利ector3f
    int DATA_RIDER_ROTATION_LOCKED = 57; //byte
    int DATA_RIDER_MAX_ROTATION = 58; //float
    int DATA_RIDER_MIN_ROTATION = 59; //float
    int DATA_AREA_EFFECT_CLOUD_RADIUS = 60; //float
    int DATA_AREA_EFFECT_CLOUD_WAITING = 61; //int
    int DATA_AREA_EFFECT_CLOUD_PARTICLE_ID = 62; //int
    /* 63 (int) shulker-related */
    int DATA_SHULKER_ATTACH_FACE = 64; //byte
    /* 65 (short) shulker-related */
    int DATA_SHULKER_ATTACH_POS = 66; //飙泪ock coords
    int DATA_TRADING_PLAYER_EID = 67; //long

    /* 69 (byte) command-block */
    int DATA_COMMAND_BLOCK_COMMAND = 70; //string
    int DATA_COMMAND_BLOCK_LAST_OUTPUT = 71; //string
    int DATA_COMMAND_BLOCK_TRACK_OUTPUT = 72; //byte
    int DATA_CONTROLLING_RIDER_SEAT_NUMBER = 73; //byte
    int DATA_STRENGTH = 74; //int
    int DATA_MAX_STRENGTH = 75; //int
    // 76 (int)
    int DATA_LIMITED_LIFE = 77;
    int DATA_ARMOR_STAND_POSE_INDEX = 78; // int
    int DATA_ENDER_CRYSTAL_TIME_OFFSET = 79; // int
    int DATA_ALWAYS_SHOW_NAMETAG = 80; // byte
    int DATA_COLOR_2 = 81; // byte
    // 82 unknown
    int DATA_SCORE_TAG = 83; //String
    int DATA_BALLOON_ATTACHED_ENTITY = 84; // long
    int DATA_PUFFERFISH_SIZE = 85;

    int DATA_FLAGS_EXTENDED = 91;

    int DATA_SKIN_ID = 103; // int ???

    // Flags
    int DATA_FLAG_ONFIRE = 0;
    int DATA_FLAG_SNEAKING = 1;
    int DATA_FLAG_RIDING = 2;
    int DATA_FLAG_SPRINTING = 3;
    int DATA_FLAG_ACTION = 4;
    int DATA_FLAG_INVISIBLE = 5;
    int DATA_FLAG_TEMPTED = 6;
    int DATA_FLAG_INLOVE = 7;
    int DATA_FLAG_SADDLED = 8;
    int DATA_FLAG_POWERED = 9;
    int DATA_FLAG_IGNITED = 10;
    int DATA_FLAG_BABY = 11; //disable head scaling
    int DATA_FLAG_CONVERTING = 12;
    int DATA_FLAG_CRITICAL = 13;
    int DATA_FLAG_CAN_SHOW_NAMETAG = 14;
    int DATA_FLAG_ALWAYS_SHOW_NAMETAG = 15;
    int DATA_FLAG_IMMOBILE = 16, DATA_FLAG_NO_AI = 16;
    int DATA_FLAG_SILENT = 17;
    int DATA_FLAG_WALLCLIMBING = 18;
    int DATA_FLAG_CAN_CLIMB = 19;
    int DATA_FLAG_SWIMMER = 20;
    int DATA_FLAG_CAN_FLY = 21;
    int DATA_FLAG_WALKER = 22;
    int DATA_FLAG_RESTING = 23;
    int DATA_FLAG_SITTING = 24;
    int DATA_FLAG_ANGRY = 25;
    int DATA_FLAG_INTERESTED = 26;
    int DATA_FLAG_CHARGED = 27;
    int DATA_FLAG_TAMED = 28;
    int DATA_FLAG_ORPHANED = 29;
    int DATA_FLAG_LEASHED = 30;
    int DATA_FLAG_SHEARED = 31;
    int DATA_FLAG_GLIDING = 32;
    int DATA_FLAG_ELDER = 33;
    int DATA_FLAG_MOVING = 34;
    int DATA_FLAG_BREATHING = 35;
    int DATA_FLAG_CHESTED = 36;
    int DATA_FLAG_STACKABLE = 37;
    int DATA_FLAG_SHOWBASE = 38;
    int DATA_FLAG_REARING = 39;
    int DATA_FLAG_VIBRATING = 40;
    int DATA_FLAG_IDLING = 41;
    int DATA_FLAG_EVOKER_SPELL = 42;
    int DATA_FLAG_CHARGE_ATTACK = 43;
    int DATA_FLAG_WASD_CONTROLLED = 44;
    int DATA_FLAG_CAN_POWER_JUMP = 45;
    int DATA_FLAG_LINGER = 46;
    int DATA_FLAG_HAS_COLLISION = 47;
    int DATA_FLAG_GRAVITY = 48;
    int DATA_FLAG_FIRE_IMMUNE = 49;
    int DATA_FLAG_DANCING = 50;
    int DATA_FLAG_ENCHANTED = 51;
    int DATA_FLAG_SHOW_TRIDENT_ROPE = 52; // tridents show an animated rope when enchanted with loyalty after they are thrown and return to their owner. To be combined with DATA_OWNER_EID
    int DATA_FLAG_CONTAINER_PRIVATE = 53; //inventory is private, doesn't drop contents when killed if true
    //public static final int TransformationComponent 54; ???
    int DATA_FLAG_SPIN_ATTACK = 55;
    int DATA_FLAG_SWIMMING = 56;
    int DATA_FLAG_BRIBED = 57; //dolphins have this set when they go to find treasure for the player
    int DATA_FLAG_PREGNANT = 58;
    int DATA_FLAG_LAYING_EGG = 59;


}
