package com.nauroo.ppg.ui.home.interactiveguide;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nauroo.ppg.R;
import com.nauroo.ppg.ui.home.HomeActivity;
import com.nauroo.ppg.ui.home.interactiveguide.certificaciones.CertificacionesFragment;
import com.nauroo.ppg.ui.home.interactiveguide.energygeneration.EnergyGenerationFragment;
import com.nauroo.ppg.ui.home.interactiveguide.gas_de_petroleo.GasDePetrolFragment;
import com.nauroo.ppg.ui.home.interactiveguide.hydroelectricplant.HydroelectricPlantFragment;
import com.nauroo.ppg.ui.home.interactiveguide.industria_ferroviaria.IndustriaFerroviariaFragment;
import com.nauroo.ppg.ui.home.interactiveguide.industrialship.IndustrialShipFragment;
import com.nauroo.ppg.ui.home.interactiveguide.marineplatform.MarinePlatformFragment;
import com.nauroo.ppg.ui.home.interactiveguide.maritimo.MaritimoFragment;
import com.nauroo.ppg.ui.home.interactiveguide.mineria.MineriaFragment;
import com.nauroo.ppg.ui.home.interactiveguide.pfp.PFPFragment;
import com.nauroo.ppg.ui.home.interactiveguide.pisos.PisosFragment;
import com.nauroo.ppg.ui.home.interactiveguide.professionalprotection.ProfessionalProtectionFragment;
import com.nauroo.ppg.ui.home.interactiveguide.refinery.RefineryFragment;
import com.nauroo.ppg.ui.home.interactiveguide.structures.StructuresFragment;
import com.nauroo.ppg.ui.home.interactiveguide.waterandtreatment.WaterAndTreatmentFragment;
import com.nauroo.ppg.ui.home.interactiveguide.waterandtreatment.WaterTreatment2;

import java.util.List;

/**
 * Created by Mohan M on 12/11/2017.
 */

public class InteractiveGuildRecyclerViewAdapter extends RecyclerView.Adapter<InteractiveGuildRecyclerViewAdapter.ViewHolder> {
    Activity context;
    List<String> interactiveGuideList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        public ViewHolder(View v) {
            super(v);
            textView=(TextView)v.findViewById(R.id.textView);
        }
    }


    public InteractiveGuildRecyclerViewAdapter(Activity context, List<String> interactiveGuideList) {
        this.context = context;
        this.interactiveGuideList=interactiveGuideList;
    }


    @Override
    public InteractiveGuildRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                             int viewType) {

        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.guide_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(interactiveGuideList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position==0){
                    MineriaFragment mineriaFragment=new MineriaFragment();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForInteractiveGuide(mineriaFragment, mineriaFragment.getClass().getName(),  interactiveGuideList.get(position));
                }
                if (position==1) {
                    MaritimoFragment interactiveGuideDetailFragement = new MaritimoFragment();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForInteractiveGuide(interactiveGuideDetailFragement, interactiveGuideDetailFragement.getClass().getName(), interactiveGuideList.get(position));
                }if (position==2){
                    GasDePetrolFragment gasDePetrolFragment=new GasDePetrolFragment();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForInteractiveGuide(gasDePetrolFragment, gasDePetrolFragment.getClass().getName(),  interactiveGuideList.get(position));
                }

                if (position==3){
                    IndustriaFerroviariaFragment secondInteractiveGuideFragment=new IndustriaFerroviariaFragment();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForInteractiveGuide(secondInteractiveGuideFragment, secondInteractiveGuideFragment.getClass().getName(),  interactiveGuideList.get(position));

                }
                if (position==4){
                    PisosFragment pisosFragment=new PisosFragment();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForInteractiveGuide(pisosFragment, pisosFragment.getClass().getName(),  interactiveGuideList.get(position));
                }
                if (position==5){
                    EnergyGenerationFragment energyGenerationFragment=new EnergyGenerationFragment();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForInteractiveGuide(energyGenerationFragment, energyGenerationFragment.getClass().getName(),  interactiveGuideList.get(position));
                }


                if (position==6){
                    StructuresFragment structuresFragment=new StructuresFragment();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForInteractiveGuide(structuresFragment, structuresFragment.getClass().getName(),  interactiveGuideList.get(position));
                }

                if (position==7){
                    PFPFragment pfpFragment=new PFPFragment();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForInteractiveGuide(pfpFragment, pfpFragment.getClass().getName(),  interactiveGuideList.get(position));
                }
                if (position==8){
                    ProfessionalProtectionFragment professionalProtectionFragment=new ProfessionalProtectionFragment();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForInteractiveGuide(professionalProtectionFragment, professionalProtectionFragment.getClass().getName(),  interactiveGuideList.get(position));
                }
                if (position==9){
                    WaterAndTreatmentFragment waterAndTreatmentFragment=new WaterAndTreatmentFragment();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForInteractiveGuide(waterAndTreatmentFragment, waterAndTreatmentFragment.getClass().getName(),  interactiveGuideList.get(position));
                }

                if (position==10){
                    WaterTreatment2 waterAndTreatmentFragment=new WaterTreatment2();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForInteractiveGuide(waterAndTreatmentFragment, waterAndTreatmentFragment.getClass().getName(),  interactiveGuideList.get(position));

                }
                if (position==11){
                    RefineryFragment refineryFragment=new RefineryFragment();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForInteractiveGuide(refineryFragment, refineryFragment.getClass().getName(),  interactiveGuideList.get(position));

                }if (position==12){

                    MarinePlatformFragment marinePlatformFragment=new MarinePlatformFragment();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForInteractiveGuide(marinePlatformFragment, marinePlatformFragment.getClass().getName(),  interactiveGuideList.get(position));


                }if (position==13){
                    IndustrialShipFragment industrialShipFragment=new IndustrialShipFragment();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForInteractiveGuide(industrialShipFragment, industrialShipFragment.getClass().getName(),  interactiveGuideList.get(position));


                }if (position==14){
                    HydroelectricPlantFragment hydroelectricPlantFragment=new HydroelectricPlantFragment();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForInteractiveGuide(hydroelectricPlantFragment, hydroelectricPlantFragment.getClass().getName(),  interactiveGuideList.get(position));

                }
                if (position==15){
                    CertificacionesFragment certificacionesFragment=new CertificacionesFragment();
                    HomeActivity instance = (HomeActivity) context;
                    instance.addFragmentForCertificacions(certificacionesFragment, certificacionesFragment.getClass().getName(),  interactiveGuideList.get(position));
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return interactiveGuideList.size();
    }
}