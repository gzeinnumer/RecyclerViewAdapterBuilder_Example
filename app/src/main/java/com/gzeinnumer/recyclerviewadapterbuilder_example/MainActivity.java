package com.gzeinnumer.recyclerviewadapterbuilder_example;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gzeinnumer.rab.helper.BindViewHolder;
import com.gzeinnumer.rab.helper.BindViewHolderEmpty;
import com.gzeinnumer.rab.helper.BindViewHolderMultiType;
import com.gzeinnumer.rab.helper.FilterCallBack;
import com.gzeinnumer.rab.model.TypeViewItem;
import com.gzeinnumer.rab.multiType.AdapterBuilderMultiType;
import com.gzeinnumer.rab.multiType.AdapterCreatorMultiType;
import com.gzeinnumer.rab.singleType.AdapterBuilder;
import com.gzeinnumer.rab.singleType.AdapterCreator;
import com.gzeinnumer.recyclerviewadapterbuilder_example.databinding.ActivityMainBinding;
import com.gzeinnumer.recyclerviewadapterbuilder_example.databinding.CustomEmptyItemBinding;
import com.gzeinnumer.recyclerviewadapterbuilder_example.databinding.RvItemBinding;
import com.gzeinnumer.recyclerviewadapterbuilder_example.databinding.RvItemGenapBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        typeSingle();
    }

    private void typeSingle() {
        List<MyModel> list = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            list.add(new MyModel(i, "Data Ke " + (i + 1)));
        }
        AdapterCreator<MyModel> adapter = new AdapterBuilder<MyModel>(R.layout.rv_item)
                .setCustomNoItem(R.layout.custom_empty_item)
                .setDivider(R.layout.custom_divider)
                .setAnimation(R.anim.anim_two)
                .setList(list)
                .onBind(new BindViewHolder<MyModel>() {
                    @Override
                    public void bind(AdapterCreator<MyModel> adapter, View holder, MyModel data, int position) {
                        //adapter.notifyDataSetChanged();

                        RvItemBinding bindingItem = RvItemBinding.bind(holder);
                        bindingItem.btn.setText(data.getId() + "_" + data.getName());
                        bindingItem.btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "tekan " + position, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
        binding.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rv.hasFixedSize();
        binding.rv.setAdapter(adapter);
    }
}