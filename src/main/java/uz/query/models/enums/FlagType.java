package uz.query.models.enums;

/**
 * Created by sherali on 12/31/15.
 */
public enum FlagType {
    None("Hammasi joyida", "Hech qanaqa ogohlantirish berilmagan"),
    Spam("Bu post spam", "Sizning postingizda spam borligi uchun, ogohlantirildingiz. Uni o'zgartiring yoki o'chiring."),
    Danger("Sayt qoidalariga zid post", "Qoidalarga zidligi uchun uchun, ogohlantirildingiz. Uni o'zgartiring yoki o'chiring."),
    Mistake("Bu postda xatolar juda ko`p", "Postinigizda turli turdagi xatolarning ko'pligi sababli, ogohlantirildingiz. Uni o'zgartiring yoki o'chiring.");

    String name;
    String label;
    String description;

    FlagType(String label, String description) {
        this.label = label;
        this.description = description;
        this.name = this.toString();
    }

    public static FlagType getTypeByName(String name) {
        for (FlagType type : FlagType.values()) {
            if (name.equals(type.getName())) {
                return type;
            }
        }
        return None;
    }

    public static boolean shouldHide(FlagType type) {
        boolean isHide = true;
        switch (type) {
//            case Danger:
//            case Spam:
//                isHide = true;
//                break;
            case None:
            case Mistake:
                isHide = false;
                break;

        }
        return isHide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
