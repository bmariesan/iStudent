//
//  TopCollectionViewCell.swift
//  Statistics
//
//  Created by Florin Ionita on 1/26/18.
//  Copyright © 2018 dp. All rights reserved.
//

import UIKit

class TopCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var yearLabel: UILabel!
    @IBOutlet weak var startsLabel: UILabel!
    @IBOutlet weak var endsLabel: UILabel!
    
    @IBOutlet weak var titleLabelTopConstraint: NSLayoutConstraint!
}
