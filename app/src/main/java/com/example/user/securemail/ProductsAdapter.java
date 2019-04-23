package info.jef.pduploader;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Product> productList;
    ClickListener clickListener;

    public ProductsAdapter(Context mCtx, List<Product> productList) {
        clickListener = (ClickListener) mCtx;
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        Product product = productList.get(position);

        //loading the image
       // Glide.with(mCtx)
          //      .load(product.getimages())
            //    .into(holder.imageView);

        holder.textViewTitle.setText(product.getfrom1());
     //   holder.textViewShortDesc.setText(product.getContact());
        holder.textViewRating.setText(String.valueOf(product.getsubject()));
       // holder.textViewPrice.setText(String.valueOf(product.getDistrict()));
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;
        LinearLayout rootLayout;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            //textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            //textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageview);
            rootLayout = itemView.findViewById(R.id.rootLayout);

        }


    }

    public interface ClickListener {
        void onClick(int position);
    }
}
