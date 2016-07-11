package jorge.palomarb.tipcalc.fragments;

import jorge.palomarb.tipcalc.models.TipRecord;

/**
 * Created by JORGE on 28/06/2016.
 */
public interface TipHistoryListFragmentListener
{
    void addToList(TipRecord record);
    void clearList();

}
