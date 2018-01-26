//
//  MainCollectionViewCell.swift
//  Statistics
//
//  Created by Florin Ionita on 1/26/18.
//  Copyright © 2018 dp. All rights reserved.
//

import UIKit
import PNChart

let TopCollectionViewCellIdentifier = "TopCollectionViewCellIdentifier"

class MainCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet weak var topCollectionView: UICollectionView!
    @IBOutlet weak var chartsHolderView: UIView!
    
    override func awakeFromNib() {
        self.layoutIfNeeded()
        
        self.topCollectionView.backgroundView = UIView.init(frame: .zero)
        self.topCollectionView.backgroundColor = .clear
        
        self.topCollectionView.dataSource = self
        self.topCollectionView.delegate = self
        
        self.setupChartsView()
    }

    fileprivate func setupChartsView() {
        let items = [PNPieChartDataItem(value: 20, color: .red, description: "None1"),
                     PNPieChartDataItem(value: 20, color: .blue, description: "None2"),
                     PNPieChartDataItem(value: 30, color: .green, description: "None3")]
        
        let pieChart = PNPieChart.init(frame: self.chartsHolderView.bounds, items: items)!
        pieChart.descriptionTextColor = .black
        pieChart.descriptionTextFont = UIFont.init(name: "Avenir-Medium", size: 14)
        pieChart.stroke()
        
        
        self.chartsHolderView.addSubview(pieChart)
    }
}

extension MainCollectionViewCell: UICollectionViewDataSource, UICollectionViewDelegate ,UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: TopCollectionViewCellIdentifier, for: indexPath)
        
        return cell
    }

    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize.init(width: UIScreen.main.bounds.width, height: UIScreen.main.bounds.height / 2)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return .zero
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 0
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumInteritemSpacingForSectionAt section: Int) -> CGFloat {
        return 0
    }
    
}
