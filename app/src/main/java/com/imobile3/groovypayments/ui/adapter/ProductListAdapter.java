package com.imobile3.groovypayments.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imobile3.groovypayments.R;
import com.imobile3.groovypayments.data.enums.GroovyColor;
import com.imobile3.groovypayments.data.enums.GroovyIcon;
import com.imobile3.groovypayments.data.model.Product;
import com.imobile3.groovypayments.rules.CurrencyRules;
import com.imobile3.groovypayments.utils.StateListHelper;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductListAdapter
        extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private final Context mContext;
    private final AdapterCallback mCallbacks;
    private List<Product> mItems;

    public interface AdapterCallback {

        void onProductClick(Product product);
    }

    public ProductListAdapter(
            @NonNull Context context,
            @NonNull List<Product> products,
            @NonNull AdapterCallback callback) {
        mContext = context;
        mCallbacks = callback;
        mItems = products;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.product_list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product item = mItems.get(position);

        // product icon
        holder.productImage.setImageResource(
                GroovyIcon.resourceFromId(item.getIconId()));
        holder.productImage.setBackgroundColor(
                mContext.getResources().getColor(GroovyColor.colorFromId(item.getColorId())));

        // product label
        holder.label.setText(item.getName());
        holder.label.setTextColor(
                StateListHelper.getTextColorSelector(mContext, R.color.black_space));

        // product price formatted
        holder.price.setText(
                new CurrencyRules().getFormattedAmount(item.getUnitPrice(), Locale.getDefault()));

        // extra notes
        if(item.getNote().isEmpty()) {
            holder.line.setVisibility(View.GONE);
        } else {
            holder.line.setVisibility(View.VISIBLE);
            holder.note.setText(item.getNote());
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ViewGroup container;
        TextView label;
        TextView price;
        TextView note;
        ImageView productImage;
        View line;

        ViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            productImage = itemView.findViewById(R.id.icon);
            label = itemView.findViewById(R.id.label);
            price = itemView.findViewById(R.id.price);
            note = itemView.findViewById(R.id.note);
            line = itemView.findViewById(R.id.line);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == container) {
                mCallbacks.onProductClick(mItems.get(getAdapterPosition()));
            }
        }
    }

    public List<Product> getItems() {
        return mItems;
    }

    public void setItems(@Nullable List<Product> items) {
        mItems = items != null ? items : new ArrayList<>();
        notifyDataSetChanged();
    }
}
