package items;

public interface iItem {

    /**
     * Get the item's name
     * @return item's identifying name
     */
    String getName();

    /**
     *
     * @return item's unique description
     */
    String getDescription();

    /**
     *
     * @return get inventory size of the item
     */
    double getSize();

    /**
     *
     * @return the item's stats
     */
//    String getStats();

    /**
     *
     * @return short identifying string
     */
    String toStringShort();

}
