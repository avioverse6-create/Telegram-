package org.telegram.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Avioverse's first offline shell. No Telegram account or network connection is required.
 * Chat, profile, gifts and the demo Stars balance are stored locally on this device.
 */
public class AvioverseHomeActivity extends Activity {
    private static final int BG = Color.rgb(12, 16, 29);
    private static final int PANEL = Color.rgb(24, 30, 48);
    private static final int TEXT = Color.rgb(245, 247, 255);
    private static final int MUTED = Color.rgb(154, 165, 190);
    private static final int ACCENT = Color.rgb(112, 102, 255);
    private LinearLayout root;
    private LinearLayout content;
    private int selectedTab = 0;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle state) {
        setTheme(org.telegram.messenger.R.style.Theme_TMessages);
        super.onCreate(state);
        prefs = getSharedPreferences("avioverse_local", MODE_PRIVATE);
        buildShell();
        showTab(0);
    }

    private void buildShell() {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(BG);
        TextView brand = new TextView(this);
        brand.setText("avioverse");
        brand.setTextColor(TEXT);
        brand.setTextSize(25);
        brand.setTypeface(Typeface.DEFAULT_BOLD);
        brand.setPadding(dp(22), dp(20), dp(22), dp(14));
        root.addView(brand, new LinearLayout.LayoutParams(-1, -2));

        FrameLayout body = new FrameLayout(this);
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.addView(content);
        body.addView(scroll, new FrameLayout.LayoutParams(-1, -1));
        root.addView(body, new LinearLayout.LayoutParams(-1, 0, 1));

        LinearLayout nav = new LinearLayout(this);
        nav.setPadding(dp(8), dp(8), dp(8), dp(12));
        nav.setBackgroundColor(Color.rgb(18, 23, 38));
        String[] labels = {"Чаты", "Настройки", "Профиль"};
        for (int i = 0; i < labels.length; i++) {
            final int tab = i;
            TextView item = new TextView(this);
            item.setText(labels[i]);
            item.setTextSize(13);
            item.setGravity(Gravity.CENTER);
            item.setPadding(dp(4), dp(14), dp(4), dp(14));
            item.setTextColor(i == selectedTab ? Color.WHITE : MUTED);
            item.setOnClickListener(v -> showTab(tab));
            nav.addView(item, new LinearLayout.LayoutParams(0, -2, 1));
        }
        root.addView(nav, new LinearLayout.LayoutParams(-1, -2));
        setContentView(root);
    }

    private void showTab(int tab) {
        selectedTab = tab;
        content.removeAllViews();
        if (tab == 0) showChats();
        else if (tab == 1) showSettings();
        else showProfile();
        // Refresh selected tab colors.
        View nav = root.getChildAt(2);
        if (nav instanceof LinearLayout) {
            LinearLayout bar = (LinearLayout) nav;
            for (int i = 0; i < bar.getChildCount(); i++) {
                ((TextView) bar.getChildAt(i)).setTextColor(i == tab ? Color.WHITE : MUTED);
            }
        }
    }

    private void showChats() {
        addHeading("Чаты", "Твоё пространство для общения");
        addCard("Пока здесь тихо", "Серверов Avioverse пока нет, поэтому переписка с другими людьми ещё не подключена. Интерфейс уже готовится.");
        addCard("Локальный режим", "Приложение открывается сразу — без номера телефона, SMS, аккаунта и регистрации.");
    }

    private void showSettings() {
        addHeading("Настройки", "Всё принадлежит Avioverse");
        addCard("⭐ Звёзды Avioverse", "Баланс: ∞\nБесконечные демонстрационные звёзды. Не являются Telegram Stars или платёжным средством.");
        addCard("🎁 Подарки Avioverse", "🐻 Мишка\n🌹 Роза\n💎 Кристалл\n🚀 Ракета\n\nПодарки пока отображаются локально и не отправляются другим пользователям.");
        addCard("Режим приложения", "Автономный. Подключение к Telegram и его аккаунтам не используется на этом стартовом экране.");
    }

    private void showProfile() {
        addHeading("Профиль", "Локальный профиль на этом устройстве");
        addText("Имя профиля");
        EditText name = new EditText(this);
        name.setSingleLine(true);
        name.setTextColor(TEXT);
        name.setHintTextColor(MUTED);
        name.setHint("Как тебя называть");
        name.setText(prefs.getString("profile_name", "Пользователь Avioverse"));
        name.setPadding(dp(14), dp(12), dp(14), dp(12));
        name.setBackground(panelBackground());
        content.addView(name, params());
        Button save = new Button(this);
        save.setText("Сохранить профиль");
        save.setOnClickListener(v -> {
            String value = name.getText().toString().trim();
            if (value.isEmpty()) value = "Пользователь Avioverse";
            prefs.edit().putString("profile_name", value).apply();
            name.setText(value);
        });
        content.addView(save, params());
        addCard("Статус", "Профиль хранится только на этом телефоне. Регистрация не нужна.");
    }

    private void addHeading(String title, String subtitle) {
        TextView h = new TextView(this);
        h.setText(title);
        h.setTextColor(TEXT);
        h.setTextSize(27);
        h.setTypeface(Typeface.DEFAULT_BOLD);
        content.addView(h, paramsWithTop(8));
        TextView sub = new TextView(this);
        sub.setText(subtitle);
        sub.setTextColor(MUTED);
        sub.setTextSize(14);
        content.addView(sub, paramsWithBottom(16));
    }

    private void addText(String value) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextColor(MUTED);
        t.setTextSize(14);
        content.addView(t, paramsWithBottom(6));
    }

    private void addCard(String title, String message) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(16), dp(16), dp(16), dp(16));
        card.setBackground(panelBackground());
        TextView h = new TextView(this);
        h.setText(title);
        h.setTextColor(TEXT);
        h.setTextSize(17);
        h.setTypeface(Typeface.DEFAULT_BOLD);
        card.addView(h);
        TextView body = new TextView(this);
        body.setText(message);
        body.setTextColor(MUTED);
        body.setTextSize(14);
        body.setPadding(0, dp(8), 0, 0);
        card.addView(body);
        content.addView(card, paramsWithBottom(12));
    }

    private GradientDrawable panelBackground() {
        GradientDrawable d = new GradientDrawable();
        d.setColor(PANEL);
        d.setCornerRadius(dp(16));
        return d;
    }

    private LinearLayout.LayoutParams params() {
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
        p.setMargins(dp(18), dp(6), dp(18), dp(12));
        return p;
    }

    private LinearLayout.LayoutParams paramsWithTop(int top) {
        LinearLayout.LayoutParams p = params();
        p.topMargin = dp(top);
        return p;
    }

    private LinearLayout.LayoutParams paramsWithBottom(int bottom) {
        LinearLayout.LayoutParams p = params();
        p.bottomMargin = dp(bottom);
        return p;
    }

    private int dp(float value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }
}
