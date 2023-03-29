/*
 * Copyright © 2023 Jason A. Donenfeld <Jason@zx2c4.com>. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.zx2c4.searchshortcut;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class ReceiveTextActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CharSequence text = getIntent().getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT);
        if (text == null || text.length() == 0)
            text = getIntent().getStringExtra(SearchManager.QUERY);
        if (text == null || text.length() == 0) {
            finish();
            return;
        }

        Intent searchIntent = new Intent(Intent.ACTION_VIEW);
        searchIntent.setData(Uri.parse("https://www.google.com/search?q=" + Uri.encode(text.toString())));
        searchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            getApplicationContext().startActivity(searchIntent);
        } catch (final Exception e) {
            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
        finish();
    }
}