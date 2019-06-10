package com.quintype.oxygen.models.storypresenter;

import android.view.View;
import com.quintype.oxygen.models.story.StoryElement;

public interface StoryElementBinder {
    void bind(StoryElement var1);

    View getView();

    boolean recreate();
}
