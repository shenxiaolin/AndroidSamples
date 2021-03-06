package com.sdwfqin.sample.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.dagger.Componet.DaggerUserComponet;
import com.sdwfqin.sample.dagger.Controller.MoneyController;
import com.sdwfqin.sample.dagger.Controller.OrderController;
import com.sdwfqin.sample.dagger.model.UserModule;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaggerActivity extends AppCompatActivity {

    @BindView(R.id.btnMakeCoffee)
    Button btnMakeCoffee;
    @BindView(R.id.tvCoffee)
    TextView tvCoffee;

    // @Inject 通常在需要依赖的地方使用这个注解，也就是针水打进屁股的过程
    @Named("debug")
    @Inject
    OrderController orderController1;
    @Named("release")
    @Inject
    OrderController orderController2;
    @Inject
    MoneyController moneyController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        ButterKnife.bind(this);

        // DaggerUserComponet是编译时生成的
        DaggerUserComponet.builder().userModule(new UserModule(this)).build().inject(this);

        btnMakeCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCoffee.setText(moneyController.payMoney());
                orderController1.order();
                orderController2.age();
            }
        });
    }
}
